# RouteX — QA & Bug Report
> Senior QA analysis for hackathon readiness. Based on full codebase audit.

---

## 🔴 CRITICAL BUGS — Fix Before Demo

### BUG-01 · `LocationTrackingService` crashes on OS restart (Android 8+)
**File:** `driver/data/service/LocationTrackingService.kt:93`

The service returns `START_STICKY`, meaning Android will restart it after a kill with a `null` intent. When `intent?.action` is `null`, neither `ACTION_START` nor `ACTION_STOP` matches, so `startForeground()` is never called. Android 8+ enforces that a foreground service must call `startForeground()` within 5 seconds of `onStartCommand()`, causing an immediate crash.

```kotlin
// CURRENT — crashes on OS-restart with null intent
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    when (intent?.action) {
        ACTION_START -> { ... startForeground() ... }
        ACTION_STOP  -> stopSelf()
    }
    return START_STICKY  // ← OS restart will call with null intent
}

// FIX — return START_NOT_STICKY (trip data is in Firestore, restart not needed)
return START_NOT_STICKY
```

---

### BUG-02 · `runBlocking` in `onDestroy` → ANR risk
**File:** `LocationTrackingService.kt:116`

`onDestroy()` calls `runBlocking { driverRepository.setOnlineStatus(...) }` on the **main thread**. Firebase RTDB writes can take 3–8 seconds on slow networks. Android considers an ANR if the main thread blocks for >5 seconds.

```kotlin
// FIX — replace with a detached coroutine using GlobalScope with timeout
override fun onDestroy() {
    if (activeRouteId.isNotBlank()) {
        serviceScope.launch {
            withTimeoutOrNull(3_000) {
                runCatching { driverRepository.setOnlineStatus(activeRouteId, false) }
            }
        }
    }
    trackingJob?.cancel()
    serviceScope.cancel()
    super.onDestroy()
}
```

---

### BUG-03 · `refreshNotification()` loses the "Stop" action button
**File:** `LocationTrackingService.kt:267`

`refreshNotification()` builds a new notification without `setContentIntent()` or the "Stop" `addAction()`. After the first location update (~3 s into a trip), the driver's persistent notification loses the stop button entirely. They cannot stop tracking from the notification tray.

```kotlin
// FIX — replicate the same intents in refreshNotification()
private fun refreshNotification(speedKmh: Int) {
    val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        PendingIntent.FLAG_IMMUTABLE else 0
    val openApp   = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), flags)
    val stopAction = PendingIntent.getService(this, 1, stopIntent(this), flags)
    val n = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("RouteX – Tracking Active")
        .setContentText("Bus $activeBusNumber  •  ${speedKmh} km/h")
        .setSmallIcon(android.R.drawable.ic_menu_mylocation)
        .setOngoing(true)
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setContentIntent(openApp)
        .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopAction)
        .build()
    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        .notify(NOTIFICATION_ID, n)
}
```

---

### BUG-04 · Pagination data never displayed in Admin Trips screen
**File:** `admin/presentation/trips/AdminTripsViewModel.kt`

`loadNextPage()` fetches trips into `_pagedTrips` but `filteredTrips` is derived only from `_tripsState` (the real-time observer). The extra pages are loaded but never shown to the user.

```kotlin
// FIX — merge paginated items into _tripsState or make filteredTrips
// read from a combined source. Simplest fix:
is Resource.Success -> {
    val current = (_tripsState.value as? UiState.Success)?.data ?: emptyList()
    _tripsState.value = UiState.Success(current + result.data)
    lastDocumentId = result.data.lastOrNull()?.id
    _hasReachedEnd.value = result.data.size < TripRepository.PAGE_SIZE
}
```

---

### BUG-05 · `SecurityException` not caught in `DriverMapViewModel`
**File:** `driver/presentation/DriverMapViewModel.kt:84`

`locationProvider.locationUpdates()` is annotated `@SuppressLint("MissingPermission")` and can throw `SecurityException` if permission is revoked after the screen opens. There is no `.catch {}` on the flow, so the `viewModelScope` coroutine silently dies, freezing the map at the last known position with no error shown.

```kotlin
// FIX
locationProvider.locationUpdates(intervalMs = LocationProvider.DRIVER_INTERVAL_MS)
    .onEach { location -> ... }
    .catch { e ->
        _routeState.value = UiState.Error("Location access lost: ${e.message}")
    }
    .launchIn(viewModelScope)
```

---

### BUG-06 · `observeCurrentUser()` emits `null` on Firestore parse failure → silent logout
**File:** `auth/data/repository/AuthRepositoryImpl.kt:119`

Inside the snapshot listener, `runCatching { User(...) }.getOrNull()` returns `null` if `UserRole.fromString()` throws (e.g., role stored as `"Admin"` with capital A instead of `"admin"`). This sends `null` to `SessionViewModel`, setting `SessionState.Unauthenticated`, navigating the user to Login mid-session.

```kotlin
// FIX — log the error and keep the previous valid user instead of emitting null
val user = runCatching { User(...) }
    .onFailure { Log.e("Auth", "Failed to parse user doc: $it") }
    .getOrNull()
if (user != null) trySend(user)  // only emit if parse succeeded
```

---

## 🟡 MEDIUM ISSUES — Fix If Time Allows

### M-01 · Delay detection false-positives in poor GPS areas
**File:** `LocationTrackingService.kt:207`

`location.speed` reports `0.0f` when the GPS has poor accuracy (tunnels, parking, dense buildings), even if the bus is moving. A bus moving through a bad signal area will be incorrectly marked `DELAYED` after 3 minutes.

**Fix:** Also check `location.accuracy` — only apply the delay logic if `location.accuracy < 50f` (within 50m accuracy). Optionally use `location.hasSpeed()` before reading speed.

---

### M-02 · `DriverViewModel.signOut()` leaves orphaned RUNNING trip
**File:** `driver/presentation/DriverViewModel.kt:168`

If `endTripUseCase(it)` fails (network timeout), `signOut.invoke()` still fires. The trip document remains in `RUNNING` status permanently, appearing as an active trip in the Admin dashboard forever.

**Fix:** Only call `signOut.invoke()` inside the `is Resource.Success` branch, or mark the trip `COMPLETED` unconditionally using a Firestore transaction/batch with the auth sign-out as a separate step.

---

### M-03 · ETA notifications only fire when app is in foreground
**File:** `eta/presentation/EtaViewModel.kt:187`

`triggerNotificationsIfNeeded()` is called from the ETA Flow collector which runs in a `ViewModel` coroutine. Once the app is backgrounded and the ViewModel is cleared, no local notifications fire. The Cloud Function proximity notifications fire but those are distance-only (no ETA context after our refactor).

**Fix:** Move the notification threshold logic to a foreground `WorkManager` task or keep a lightweight `BroadcastReceiver` that the Cloud Function can trigger with `etaMinutes` as payload data. For hackathon: acceptable if demoed with app in foreground.

---

### M-04 · Default bus number "BUS-01" shared by all drivers who skip the field
**File:** `driver/presentation/DriverViewModel.kt:96`

`val bus = _busNumber.value.trim().ifBlank { "BUS-01" }` means every driver who doesn't fill in their bus number shows as "BUS-01". Students tracking "BUS-01" will see multiple phantom buses on the map.

**Fix:** Show a validation error in `DriverScreen` if the field is blank, blocking trip start until a real bus number is entered.

---

### M-05 · Multiple `MapViewModel` instances register duplicate geofences
**File:** `maps/presentation/MapViewModel.kt:114`

Every `MapScreen` composable that is created (e.g., navigating back and forward) calls `geofenceManager.registerStopGeofences(stops)`. The `GeofencingClient` will reject duplicate IDs, but only after a roundtrip to Google Play Services. More critically, `removeAll()` in `onCleared()` removes ALL registered geofences, including those registered by a still-active MapViewModel.

**Fix:** Check if geofences are already registered before calling `registerStopGeofences()`, or use `removeAll()` before re-registering.

---

### M-06 · `AdminAnalytics` flickers on every load (random data)
**File:** `admin/data/repository/AdminRepositoryImpl.kt:95`

`buildHourlyActivity()` calls `(20..35).random()` for each hourly slot. Every time `getAnalyticsSnapshot()` is called (including on recomposition), the chart values change randomly.

**Fix:** Seed the random with a stable value (e.g., `Random(routeCount)`) or use fixed mock values for demo.

---

### M-07 · `EtaViewModel.notifiedThresholds` isn't cleared on route stop state change
**File:** `eta/presentation/EtaViewModel.kt:73`

`notifiedThresholds` is cleared when the user changes route/stop but NOT when the bus goes offline and comes back online. If the bus briefly goes offline (driver kills app) then reconnects, no notifications fire for thresholds already seen in that session.

**Fix:** Clear `notifiedThresholds` when `eta.status == BusStatus.OFFLINE` transitions to any online status.

---

### M-08 · `AdminRepositoryImpl` injects `FirebaseFirestore` directly alongside data sources
The analytics and dashboard stats methods in the new `AdminRepositoryImpl` still do raw Firestore queries inline, bypassing the data source pattern introduced in the refactor. This is an architectural inconsistency.

**Fix:** Move analytics to an `AnalyticsDataSource` class.

---

## ⚪ MINOR ISSUES

| # | File | Issue |
|---|------|-------|
| MIN-01 | `LocationTrackingService.kt:147` | Foreground notification shows raw `routeId` (Firestore document ID) instead of human-readable route name |
| MIN-02 | `DriverMapScreen.kt` | No loading state shown while waiting for first GPS fix — bottom card shows "—" with no explanation |
| MIN-03 | `MapViewModel.kt:148` | `.catch { /* swallow stream errors */ }` — silent RTDB read failure, map freezes with no user feedback |
| MIN-04 | `DriverMapViewModel.kt` | `stops` StateFlow derives from `_routeState` using `.map {}` but the collect happens lazily — there's a window where `updateNextStop()` runs before stops are populated, emitting null stop |
| MIN-05 | `AdminTripsScreen.kt` | Pagination trigger fires even when `filteredTrips` is filtered (e.g., only RUNNING trips shown) — loads more ALL trips but filter hides them |
| MIN-06 | `GeofenceManager` | `events` is a companion `SharedFlow` — it survives ViewModel lifecycle. A delayed geofence event from a previous trip can trigger campus arrival UI in a new session |
| MIN-07 | `EtaScreen` | If route has no stops (`route.stops.isEmpty()`), `restoreOrSelectFirstStop()` calls `onStopSelected()` with `null` — NPE risk if null stop is passed to FCM subscription |
| MIN-08 | `DriverScreen.kt` | `AnimatedVisibility(visible = isTracking && selectedRoute != null)` — if `isTracking` is true but `selectedRoute` is null (race condition on re-subscribe), "View Map" button never appears |
| MIN-09 | `functions/index.js` | Renamed function to `sendProximityNotifications` but `firebase.json` / Cloud Function deployment config likely still references old `sendEtaNotifications` name — deployment will fail until updated |
| MIN-10 | `TripRepositoryImpl.kt` | `observeAllTrips()` uses `.limit(50)` hardcoded — with pagination now added, the real-time observer and paginated fetch can return inconsistent page boundaries |

---

## 📱 DEVICE TEST CASES

### TC-01: Role Guard — Unauthorised Navigation
**Goal:** Verify a student cannot access admin screens.
1. Log in as Student role
2. Manually navigate via ADB: `adb shell am start -n com.routex.app/.MainActivity --es "deeplink" "admin_dashboard"`
3. **Expected:** Screen shows shimmer briefly → redirects to `student_dashboard`
4. **Verify:** No admin data is visible at any point

---

### TC-02: Driver Trip — Full Lifecycle (RUNNING → DELAYED → RUNNING → COMPLETED)
**Goal:** Verify all trip state transitions work end-to-end.
1. Log in as Driver, select a route, enter bus number "KA-01-1234", tap **Start Trip**
2. **Expected:** Firestore `trips/{id}.status == "running"`, foreground service notification visible with **Stop** button
3. Lock the device for 4 minutes (simulates bus stop)
4. **Expected:** After 3 minutes, Firestore `trips/{id}.status == "delayed"`
5. Unlock and move the device (walk around)
6. **Expected:** Status returns to `"running"` within one location update cycle (~3 s)
7. Walk to within 300 m of KLS GIT coordinates (15.8637, 74.5098)
8. **Expected:** Status changes to `"completed"`, notification fires "Arrived at KLS GIT", foreground service stops

---

### TC-03: Student ETA — Stop Selection & Notifications
**Goal:** Verify ETA screen shows live data and fires notifications.
1. Log in as Student, tap a route card, open ETA screen
2. Select a boarding stop from the list
3. Verify ETA countdown appears (not "—" or "Unknown")
4. Have driver start trip on a device with location GPS active
5. Walk the driver device toward the student's boarding stop
6. **Expected at ~1.5 km:** No notification yet
7. **Expected at ~400 m:** "Bus is almost here" local notification fires
8. **Expected at ~100 m:** "Bus has arrived" local notification fires
9. Verify notifications fire **only once** per threshold (not on every location update)

---

### TC-04: Offline Resilience — Student Dashboard
**Goal:** Verify offline banner appears and app doesn't crash without internet.
1. Log in as Student with internet
2. Open Student Dashboard — verify routes list loads
3. Enable Airplane Mode
4. **Expected:** Red "Offline Mode" banner slides in from top
5. **Expected:** Existing routes list is still visible (Firestore cache)
6. Tap a route card — **Expected:** Map screen opens, last known bus location visible (not blank)
7. Re-enable network
8. **Expected:** Banner disappears, live data resumes within ~5 seconds

---

### TC-05: Offline Resilience — Driver Trip Start
**Goal:** Verify driver cannot silently start a "ghost" trip offline.
1. Log in as Driver, enable Airplane Mode
2. Select a route, tap **Start Trip**
3. **Expected:** Error snackbar "Failed to start trip" (or no spinner stuck)
4. **Expected:** `_isTracking.value` remains `false`, service is NOT started
5. Re-enable network, tap **Start Trip** again
6. **Expected:** Trip starts normally, Firestore document created

---

### TC-06: Admin CRUD — Bus + Driver Assignment
**Goal:** Verify admin can create a bus, assign it to a route and driver.
1. Log in as Admin, navigate to **Buses**
2. Tap FAB → create bus "TEST-01" with all required fields
3. **Expected:** Bus appears in list within 2 seconds (Firestore snapshot)
4. Navigate to **Manage Routes**, select a route
5. Assign "TEST-01" and a test driver
6. **Expected:** Route document updated: `busId = TEST-01`, `driverId = [selected]`
7. **Expected:** Bus document updated: `assignedRouteId = [routeId]`, `assignedDriverId = [driverId]`
8. **Expected:** Driver document updated: `assignedRouteId = [routeId]`
9. Delete the bus "TEST-01"
10. **Expected:** Bus removed from list, no orphaned references crash the app

---

### TC-07: Driver Map Screen — Camera Follow
**Goal:** Verify driver map shows correct route and auto-follows GPS.
1. Log in as Driver, start a trip on Route A
2. Tap **View Route Map**
3. **Expected:** Map loads, all route stops shown as markers, blue polyline visible
4. **Expected:** Driver's position marker (azure) is visible at current GPS location
5. Walk (or move) the device
6. **Expected:** Camera smoothly follows the driver within 2 seconds
7. Tap anywhere on the map (disables auto-follow)
8. Move the device again
9. **Expected:** Camera does NOT follow (manual override active)
10. Tap the re-center FAB (bottom-right)
11. **Expected:** Camera snaps back to current driver position, auto-follow re-enabled

---

### TC-08: Real-Time Map — Bus Marker Animation
**Goal:** Verify bus marker moves smoothly without jumping.
1. Log in as Student, open Map for an active route
2. Observe the bus marker as it updates every ~3 seconds
3. **Expected:** Marker glides to new position (animated LatLng transition), not instant teleport
4. Kill the driver app
5. **Expected:** Bus marker stays at last known position; no crash, no blank map

---

### TC-09: Admin Trip Monitor — Pagination
**Goal:** Verify trip list loads more items on scroll.
1. Log in as Admin, navigate to **Trip Monitor**
2. Ensure >20 trips exist in Firestore (seed data if needed)
3. Scroll to the bottom of the list
4. **Expected:** Loading spinner appears briefly, then more trips load below
5. Continue scrolling until all trips are loaded
6. **Expected:** Spinner disappears (no more items), `hasReachedEnd = true`

---

### TC-10: FCM Notification — Background App
**Goal:** Verify proximity notifications fire even with app closed.
1. Log in as Student, select a boarding stop, background/close the app
2. Have driver start a trip and drive toward the student's stop
3. When driver is within ~1.5 km of the stop
4. **Expected:** FCM notification appears in notification tray: "Bus arriving soon — [X] km away"
5. **Expected:** Notification fires only once per threshold (cooldown works)

---

### TC-11: RBAC — Firestore Rule Enforcement
**Goal:** Verify backend rules block unauthorized writes even if UI is bypassed.
1. Using Firebase console or REST API, attempt to write to `trips/` as a Student auth token
2. **Expected:** Write rejected with PERMISSION_DENIED
3. Attempt to write to `bus_locations/routeA` as a Student token
4. **Expected:** RTDB write rejected
5. Attempt to write as a Driver to a route they're not assigned to
6. **Expected:** RTDB write rejected (assignedRouteId mismatch)

---

### TC-12: Session Expiry / Token Revocation
**Goal:** Verify app handles mid-session auth changes gracefully.
1. Log in as Student
2. In Firebase Console, revoke the user's session token
3. Return to the app and perform any action (e.g., scroll routes list)
4. **Expected:** App shows error state or redirects to Login; no crash; no blank infinite-loading screen

---

## Summary Scorecard

| Category | Status | Notes |
|---|---|---|
| Role-based access | ✅ Mostly safe | BUG-06 can cause silent logout; TC-01 to verify |
| Real-time tracking | ⚠️ Medium risk | M-01 false delays; BUG-05 frozen map on perm revoke |
| Driver flow | 🔴 Critical | BUG-01, BUG-02, BUG-03 must fix |
| Student ETA | ⚠️ Medium risk | M-03 background notifications unreliable |
| Admin operations | ✅ Functional | M-06 analytics flicker; BUG-04 pagination |
| Offline behavior | ✅ Good | Banner added; Firestore cache works |
| Map accuracy | ⚠️ Medium risk | MIN-03, M-05 geofence duplicates |
| Notifications | ⚠️ Medium risk | Works foreground only; FCM works always |
| Trip lifecycle | ⚠️ Medium risk | M-02 orphaned trips; BUG-01 crash |
