# RouteX — Hackathon Submission Guide

## Quick APK Build (No Keystore)

```powershell
# From the RouteX directory:
.\gradlew assembleHackathon
# Output: app/build/outputs/apk/hackathon/app-hackathon.apk
```

## Signed Release APK

1. Generate a keystore (one-time):
```bash
keytool -genkey -v -keystore routex-release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias routex
```

2. Add to `RouteX/local.properties`:
```properties
MAPS_API_KEY=AIzaSyBCba3f7jlQZhuMWSt7DUIk_gp5SkpKWAU
KEY_STORE_FILE=routex-release.jks
KEY_STORE_PASSWORD=<your_password>
KEY_ALIAS=routex
KEY_PASSWORD=<your_password>
```

3. Build:
```powershell
.\gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release.apk
```

## Demo Flow (for judges)

### Admin Demo
1. Launch app → Register as **Admin**
2. Open **Admin Panel → Seed Demo Data** (cloud upload icon)
3. Explore: **Route Editor** (tap map to add stops), **Analytics**, **Emergency Alerts**

### Driver Demo
1. Register as **Driver** on a second device (or emulator)
2. Select a route, enter bus number, tap **Start Tracking**
3. GPS updates every 3 s to Firebase Realtime Database

### Student Demo
1. Register as **Student**
2. Tap **All Routes** → select a route → **Track ETA**
3. Select your boarding stop — watch countdown update live
4. Notifications fire at 10 min, 5 min, 2 min thresholds

## Firebase Setup Checklist
- [ ] Enable **Authentication** (Email/Password)
- [ ] Create **Firestore** database (production rules)
- [ ] Enable **Realtime Database** at `routex-85324-default-rtdb.firebaseio.com`
- [ ] Enable **Cloud Messaging** (FCM)
- [ ] Deploy Cloud Functions: `cd functions && npm install && firebase deploy --only functions`
- [ ] Add SHA-1 fingerprint in Firebase Console → Project Settings → Android app

## Feature Highlights
| Feature | Status |
|---|---|
| Email auth + role-based login | ✅ |
| Real-time bus tracking (RTDB) | ✅ |
| Smooth marker animation | ✅ |
| Polyline route progress (completed/remaining) | ✅ |
| Geofencing (200m radius) | ✅ |
| Background GPS tracking (Foreground Service) | ✅ |
| Intelligent ETA prediction (speed cache + traffic) | ✅ |
| Missed bus prediction | ✅ |
| Push notifications (local + FCM) | ✅ |
| Admin CRUD: Routes, Buses, Drivers | ✅ |
| **Map-based route editor** (tap to add stops) | ✅ |
| Emergency Alert broadcast | ✅ |
| Analytics dashboard (charts, KPIs) | ✅ |
| Dark/light theme | ✅ |
| ProGuard + signed APK ready | ✅ |
