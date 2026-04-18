# RouteX Project Analysis

---

## 1. Overview

**RouteX** is a production-grade Android campus bus tracking application built for KLS Gogte Institute of Technology, Belagavi. It provides real-time GPS tracking, ETA prediction, trip lifecycle management, and role-based dashboards for three user classes.

### Key Features Implemented

- Email-based Firebase Authentication with role-based routing (Student / Driver / Admin)
- Live bus tracking via Firebase Realtime Database with smooth marker animation
- Intelligent ETA prediction using Haversine distance, rolling speed cache, and time-of-day traffic factors
- Full trip lifecycle: `NOT_STARTED → RUNNING → DELAYED → COMPLETED` with auto-detection
- OS-level and software geofencing around bus stops and the college campus
- Campus auto-completion: bus entering KLS GIT radius automatically ends the trip
- Admin CRUD for routes, buses, and drivers; map-based route editor; analytics dashboard; emergency alerts
- Firebase Cloud Messaging (FCM) push notifications with server-side Cloud Functions
- Missed bus prediction and boarding stop selection
- Dark/light theme toggle persisted via DataStore

---

## 2. Architecture

### Pattern
**MVVM + Clean Architecture** — strict three-layer separation enforced across all modules.

### Module Structure
```
app/
├── auth/          presentation | domain | data
├── student/       presentation | domain | data
├── driver/        presentation | domain | data
├── admin/         presentation | domain | data
├── maps/          presentation | domain | data
├── eta/           presentation | domain
├── trips/         domain | data
└── core/          base | di | navigation | notification | rbac | location | ui
```
- **116 Kotlin source files** across **65 packages**
- Domain layer has zero Android dependencies — pure Kotlin models and interfaces
- Each module owns its Hilt `@Module` binding; `AppModule` provides only shared singletons

### State Management
- **`StateFlow`** exclusively — no `LiveData` used anywhere
- `UiState<T>` sealed class (`Idle / Loading / Success / Error`) used consistently across all ViewModels
- `Resource<T>` sealed class (`Loading / Success / Error`) in the data layer

### Dependency Injection
- **Hilt 2.51.1** throughout — `@HiltViewModel`, `@Singleton`, `@ApplicationContext`
- `SessionViewModel` scoped at the NavGraph root for real-time session observation
- `CalculateEtaUseCase` correctly scoped as stateful singleton per ViewModel

---

## 3. Tech Stack

| Category | Library / Version |
|---|---|
| Language | Kotlin 1.9.24 |
| UI | Jetpack Compose BOM 2024.06.00, Material 3 |
| Architecture | AndroidX Lifecycle 2.8.3, ViewModel |
| Navigation | Navigation Compose 2.7.7 |
| DI | Hilt 2.51.1 |
| Async | Kotlin Coroutines 1.8.1, Flow |
| Local Storage | DataStore Preferences 1.1.1 |
| Maps | Maps Compose 4.3.3, Play Services Maps 18.2.0 |
| Location | Play Services Location 21.3.0 (FusedLocationProviderClient) |
| Geofencing | Android GeofencingClient |
| Permissions | Accompanist Permissions 0.34.0 |

### Firebase Services
- **Authentication** — email/password
- **Cloud Firestore** — users, routes, buses, drivers, trips, subscriptions, emergency alerts
- **Realtime Database** — live `bus_locations`, `user_roles`, `driver_assignments`
- **Cloud Messaging (FCM)** — push notifications via server-side Cloud Functions (Node.js)

---

## 4. Feature Breakdown

| Feature | Status | Notes |
|---|---|---|
| Authentication | ✅ Complete | Email/password; role fetched from Firestore on login |
| Role-based navigation | ✅ Complete | `RoleGuard` composable blocks wrong-role screen access; `SessionViewModel` observes live role changes |
| Student module | ✅ Complete | Dashboard, route list, live map, ETA tracker, live trip status cards |
| Driver module | ✅ Complete | Route selection, start/end trip, foreground GPS service, trip status chip |
| Admin dashboard | ✅ Complete | CRUD: routes, buses, drivers; map-based route editor; analytics; emergency alerts; trip monitor; approaching-campus view |
| Real-time tracking | ✅ Complete | RTDB stream → `MapViewModel`; smooth `Animatable<Double>` marker animation; completed/remaining polylines |
| ETA system | ✅ Complete | `CalculateEtaUseCase`: Haversine, 6-reading speed cache, traffic multiplier (0.85×–1.55×); `MissedBusPredictionUseCase` |
| Trip lifecycle | ✅ Complete | `TripRepository` (Firestore); auto-DELAYED after 3 min stationary; auto-COMPLETED on campus arrival |
| Notifications | ✅ Complete | FCM tokens stored in Firestore; Cloud Function sends ETA alerts; local notifications for geofence and campus arrival |
| Geofencing | ✅ Complete | OS `GeofencingClient` + software proximity check; distinct campus geofence (300 m) |
| Central hub (KLS GIT) | ✅ Complete | `CollegeHub` constants; gold map marker; "Arriving at College" animated banner; admin near-campus bus list |
| Offline support | ⚠️ Partial | RTDB persistence enabled; Firestore offline cache relies on default SDK behaviour — no explicit cache-first strategy |
| Unit tests | ❌ Absent | No test files present |

---

## 5. Database Design

### Cloud Firestore
```
users/{uid}            — name, email, role, createdAt
routes/{routeId}       — routeNumber, stops[], isActive, schedule
buses/{busId}          — number, capacity, assignedRouteId, assignedDriverId
drivers/{driverId}     — name, licenseNumber, assignedRouteId, busNumber
trips/{tripId}         — busId, driverId, routeId, status, startTime, endTime
subscriptions/{uid}    — routeId, stopId, fcmToken
emergency_alerts/{id}  — type, message, routeId, resolvedAt
```

### Realtime Database
```
bus_locations/{routeId}          — latitude, longitude, speed, heading, isOnline
user_roles/{uid}                 — role (mirrored from Firestore by Cloud Function)
driver_assignments/{uid}         — assignedRouteId, busNumber
```

### Security Model
- Firestore rules enforce role-based access using `request.auth.token.role` (Firebase Auth custom claims)
- RTDB write rule for `bus_locations`: requires `token.role == 'driver'` AND `driver_assignments/{uid}/assignedRouteId == $routeId`
- Three Cloud Functions: `setUserRole`, `updateUserRole`, `setDriverAssignment` — maintain custom claims and RTDB mirrors

---

## 6. Strengths

- **Strict Clean Architecture** — domain layer is completely decoupled from Android; every use case is independently testable
- **End-to-end type safety** — `Resource<T>` and `UiState<T>` prevent unchecked nulls at the boundary between layers
- **Production-grade RBAC** — both UI (`RoleGuard`) and backend (Firestore + RTDB rules + custom claims) enforce roles independently, so no single bypass point
- **Intelligent ETA engine** — traffic-factor-adjusted, rolling-average speed, graceful degradation when bus is stopped
- **Auto trip lifecycle** — no manual intervention needed; delay detection and campus arrival completion are fully automatic
- **Hackathon-ready features** — Emergency Alert system, Missed Bus Prediction, Analytics Dashboard, and map-based Route Editor are all functional and UI-polished

---

## 7. Gaps / Missing Features

| Gap | Risk Level |
|---|---|
| **No unit or integration tests** | High — regression risk during demo setup |
| **`CalculateEtaUseCase` is `@Singleton`** | Medium — shared state across ViewModel instances; should be `@ActivityRetainedScoped` |
| **No Firestore pagination** | Medium — `trips` and `routes` queries use `.limit(50)` hardcoded; large collections will degrade |
| **`MapViewModel` imports `Route` without explicit import** | Low — likely compiles via transitive import; fragile to dependency changes |
| **FCM `sendEtaNotifications` Cloud Function** deploys to `asia-south1` but ETA threshold logic duplicates client-side calculation | Low — maintenance burden; risk of drift |
| **Offline-first strategy absent** — no `WorkManager` or explicit cache policy for routes/stops | Medium — demo will fail entirely with no network |
| **No driver-facing map** — drivers have no visual confirmation of their route on screen | Low for demo |
| **Splash → Login navigation** uses `popUpTo(0)` correctly, but `RoleGuard` redirect and back-press handling on deep links is untested | Medium |

---

## 8. Code Quality Review

### Modularity
**Excellent.** Each feature module is self-contained with its own DI module, domain interfaces, and repository implementation. The `core/` module correctly contains only cross-cutting concerns (navigation, notification, RBAC, location constants, UI primitives).

### Scalability
**Good.** The `TripRepository`, `AdminRepository`, and `MapsRepository` are all interface-backed, making Firebase swappable. The RTDB write-rule design (custom claims + mirrored assignments) scales to multiple concurrent drivers without race conditions.

### Readability
**Good overall.** Naming is consistent — `*UseCase`, `*ViewModel`, `*Screen`, `*Repository` patterns followed throughout. Minor concern: `AdminRepositoryImpl` is very large (~300+ lines) and would benefit from splitting bus, driver, and alert operations into separate data sources.

---

## 9. Recommendations (Pre-Demo)

1. **Add `@ActivityRetainedScoped` to `CalculateEtaUseCase`** — prevents state leakage between ETA sessions if the ViewModel is recreated. One-line annotation change.

2. **Seed demo Firebase data before the presentation** — use the existing `FirebaseInitializer.seedDemoRoute()` call from the Admin dashboard FAB. Verify RTDB and Firestore have at least one active route, two stops, and a driver assigned.

3. **Test the full trip flow end-to-end on a physical device** — emulators cannot trigger `FusedLocationProviderClient` reliably. Walk through: Login as Driver → Start Trip → Login as Student (second device) → verify live map updates, ETA counts down, trip auto-completes on campus entry.

4. **Add a network-unavailable `EmptyState`** in `MapScreen` and `EtaScreen` — catch the `Flow` error state and show a clear "No internet connection" message instead of a silent spinner, reducing demo embarrassment risk.

5. **Extract `AdminRepositoryImpl` into sub-data-sources** (e.g., `BusDataSource`, `DriverDataSource`) — reduces the single-class risk of a Firestore call error bringing down unrelated admin operations, and improves readability during code review at the hackathon.
