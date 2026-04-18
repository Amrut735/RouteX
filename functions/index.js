/**
 * RouteX – Firebase Cloud Functions
 *
 * Trigger: Realtime Database write at /bus_locations/{routeId}
 * Action : Read all active student subscriptions for this route from Firestore,
 *          calculate ETA for each subscriber's boarding stop, and send an FCM
 *          push notification when the bus is within notification thresholds.
 *
 * Firestore schema (written by the Android FcmTokenRepository):
 *   subscriptions/{userId} {
 *       uid:      string
 *       routeId:  string
 *       stopId:   string
 *       stopName: string
 *       fcmToken: string
 *       active:   boolean
 *       stopLat:  number   ← populated if you store it; otherwise read from routes
 *       stopLng:  number
 *   }
 *
 * RTDB schema (written by LocationTrackingService):
 *   bus_locations/{routeId} {
 *       latitude:    number
 *       longitude:   number
 *       speed:       number  (km/h)
 *       isOnline:    boolean
 *       lastUpdated: number
 *   }
 */

const { onValueWritten }     = require("firebase-functions/v2/database");
const { onDocumentCreated,
        onDocumentUpdated }  = require("firebase-functions/v2/firestore");
const { initializeApp }      = require("firebase-admin/app");
const { getFirestore }       = require("firebase-admin/firestore");
const { getMessaging }       = require("firebase-admin/messaging");
const { getAuth }            = require("firebase-admin/auth");
const { getDatabase }        = require("firebase-admin/database");

initializeApp();

const db        = getFirestore();
const messaging = getMessaging();

// Minimum interval between notifications for the same (user, threshold) pair (ms)
const NOTIFY_COOLDOWN_MS = 5 * 60 * 1000; // 5 minutes

// In-memory cooldown store (reset on function cold start — acceptable for our scale)
const notifyCooldowns = new Map();

// ── Helper: Haversine distance in km ─────────────────────────────────────────
function haversine(lat1, lon1, lat2, lon2) {
  const R    = 6371;
  const dLat = toRad(lat2 - lat1);
  const dLon = toRad(lon2 - lon1);
  const a    = Math.sin(dLat / 2) ** 2 +
    Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(dLon / 2) ** 2;
  return 2 * R * Math.asin(Math.sqrt(a));
}
const toRad = (deg) => (deg * Math.PI) / 180;

// ── Helper: Cooldown check ────────────────────────────────────────────────────
function isCoolingDown(uid, threshold) {
  const key  = `${uid}:${threshold}`;
  const last = notifyCooldowns.get(key) ?? 0;
  if (Date.now() - last < NOTIFY_COOLDOWN_MS) return true;
  notifyCooldowns.set(key, Date.now());
  return false;
}

// ═══════════════════════════════════════════════════════════════════════════════
//  RBAC — setUserRole
//
//  Triggered when a new user document is created in Firestore `users/{uid}`.
//  Reads the `role` field and:
//    1. Sets a Firebase Auth custom claim  → used by Firestore + RTDB rules
//    2. Mirrors the role to RTDB `user_roles/{uid}` → RTDB rules backup
//
//  This is the ONLY way roles are applied to the Auth token — clients cannot
//  call setCustomUserClaims() directly.
// ═══════════════════════════════════════════════════════════════════════════════
exports.setUserRole = onDocumentCreated(
  { document: "users/{uid}", region: "asia-south1" },
  async (event) => {
    const uid  = event.params.uid;
    const data = event.data?.data();
    if (!data) return null;

    const role = (data.role || "student").toLowerCase();
    const validRoles = ["admin", "driver", "student"];
    if (!validRoles.includes(role)) {
      console.warn(`[RBAC] Unknown role '${role}' for uid=${uid} — defaulting to student`);
    }
    const safeRole = validRoles.includes(role) ? role : "student";

    try {
      // 1. Set custom claim on the Firebase Auth token
      await getAuth().setCustomUserClaims(uid, { role: safeRole });
      console.log(`[RBAC] Custom claim set: uid=${uid}, role=${safeRole}`);

      // 2. Mirror to RTDB so RTDB rules can read it without a Firestore cross-read
      await getDatabase()
        .ref(`user_roles/${uid}`)
        .set({ role: safeRole, updatedAt: Date.now() });

      return null;
    } catch (err) {
      console.error(`[RBAC] Failed to set role for uid=${uid}:`, err);
      return null;
    }
  }
);

// ── updateUserRole ─────────────────────────────────────────────────────────────
// Called when an admin updates a user's role field in Firestore.
// Re-applies the custom claim so the RTDB rule stays in sync.
exports.updateUserRole = onDocumentUpdated(
  { document: "users/{uid}", region: "asia-south1" },
  async (event) => {
    const uid    = event.params.uid;
    const before = event.data?.before?.data();
    const after  = event.data?.after?.data();

    // Only act if the role actually changed
    if (!before || !after || before.role === after.role) return null;

    const safeRole = ["admin", "driver", "student"].includes(after.role)
      ? after.role : "student";

    try {
      await getAuth().setCustomUserClaims(uid, { role: safeRole });
      await getDatabase()
        .ref(`user_roles/${uid}`)
        .set({ role: safeRole, updatedAt: Date.now() });
      console.log(`[RBAC] Role updated: uid=${uid}, ${before.role} → ${safeRole}`);
    } catch (err) {
      console.error(`[RBAC] Failed to update role for uid=${uid}:`, err);
    }
    return null;
  }
);

// ── setDriverAssignment ────────────────────────────────────────────────────────
// Called when an admin sets `assignedRouteId` on a driver document.
// Mirrors the assignment to RTDB `driver_assignments/{uid}` so the RTDB
// write rule can verify the driver is assigned to the route they are updating.
exports.setDriverAssignment = onDocumentUpdated(
  { document: "drivers/{driverId}", region: "asia-south1" },
  async (event) => {
    const driverId = event.params.driverId;
    const before   = event.data?.before?.data();
    const after    = event.data?.after?.data();
    if (!after) return null;

    const routeId   = after.assignedRouteId || "";
    const busNumber = after.busNumber || "";

    if (before?.assignedRouteId === routeId) return null; // no change

    try {
      await getDatabase()
        .ref(`driver_assignments/${driverId}`)
        .set({ assignedRouteId: routeId, busNumber, updatedAt: Date.now() });
      console.log(`[RBAC] Driver assignment mirrored: uid=${driverId}, route=${routeId}`);
    } catch (err) {
      console.error(`[RBAC] Driver assignment failed for uid=${driverId}:`, err);
    }
    return null;
  }
);

// ═══════════════════════════════════════════════════════════════════════════════
//  sendProximityNotifications
//
//  Single source of truth: ETA is calculated ONLY on the Android client
//  (CalculateEtaUseCase). This function triggers notifications using purely
//  distance thresholds so there is no formula duplication between client and server.
//
//  Thresholds (straight-line distance to the subscriber's chosen stop):
//    < 100 m  →  "Bus has arrived"
//    < 400 m  →  "Bus is almost here"  (~1–2 min walk)
//    < 1.5 km →  "Bus is 5 min away"
//    < 3 km   →  "Bus is 10 min away"
// ═══════════════════════════════════════════════════════════════════════════════
exports.sendProximityNotifications = onValueWritten(
  { ref: "/bus_locations/{routeId}", region: "asia-south1" },
  async (event) => {
    const routeId = event.params.routeId;
    const bus     = event.data.after.val();

    if (!bus || !bus.isOnline) return null;

    const { latitude: busLat, longitude: busLng } = bus;

    // ── Fetch active subscriptions for this route ─────────────────────────────
    const snapshots = await db
      .collection("subscriptions")
      .where("routeId", "==", routeId)
      .where("active", "==", true)
      .get();

    if (snapshots.empty) return null;

    const messages = [];

    for (const doc of snapshots.docs) {
      const { uid, fcmToken, stopName, stopLat, stopLng } = doc.data();
      if (!fcmToken || !stopLat || !stopLng) continue;

      // Distance-only threshold — no speed, no traffic factor (client owns ETA)
      const distKm    = haversine(busLat, busLng, stopLat, stopLng);
      const distM     = distKm * 1000;

      let threshold = null;
      let title     = null;
      let body      = null;

      if (distM < 100) {
        threshold = "arriving";
        title = "🚌 Bus has arrived!";
        body  = `Your bus is now at ${stopName}.`;
      } else if (distM < 400) {
        threshold = "near";
        title = "🚌 Bus is almost here!";
        body  = `Hurry to ${stopName} — bus is ${Math.round(distM)} m away.`;
      } else if (distKm < 1.5) {
        threshold = "5min";
        title = "🚌 Bus arriving soon";
        body  = `Head to ${stopName} now — bus is ${distKm.toFixed(1)} km away.`;
      } else if (distKm < 3.0) {
        threshold = "10min";
        title = "🚌 Bus is on its way";
        body  = `${stopName} — bus is about ${distKm.toFixed(1)} km away.`;
      }

      if (!threshold) continue;
      if (isCoolingDown(uid, threshold)) continue;

      messages.push({
        token: fcmToken,
        notification: { title, body },
        data: {
          routeId,
          stopName,
          distanceMeters: String(Math.round(distM)),
          type: "proximity_alert",
        },
        android: {
          priority: "high",
          notification: { channelId: "routex_eta", priority: "max" },
        },
      });
    }

    if (messages.length === 0) return null;

    // ── Batch send (FCM max 500 per call) ─────────────────────────────────────
    for (let i = 0; i < messages.length; i += 500) {
      const result = await messaging.sendEach(messages.slice(i, i + 500));
      console.log(
        `[RouteX] route=${routeId} sent=${result.successCount} failed=${result.failureCount}`
      );
    }

    return null;
  }
);
