# ════════════════════════════════════════════════════════════════════════════
#  RouteX ProGuard / R8 rules — Hackathon Release Build
# ════════════════════════════════════════════════════════════════════════════

# ── Hilt ─────────────────────────────────────────────────────────────────────
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.HiltAndroidApp class * { *; }
-keep @dagger.hilt.android.AndroidEntryPoint class * { *; }
-keepclassmembers class * {
    @dagger.hilt.android.lifecycle.HiltViewModel <methods>;
}

# ── Kotlin ────────────────────────────────────────────────────────────────────
-keepattributes Signature
-keepattributes *Annotation*
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings { <fields>; }
-keepclassmembers class kotlin.Lazy { <methods>; }

# ── Kotlin Coroutines ─────────────────────────────────────────────────────────
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# ── Firebase ──────────────────────────────────────────────────────────────────
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-keepattributes EnclosingMethod
-keep class com.google.firebase.database.** { *; }
-keepclassmembers class com.google.firebase.database.** { *; }

# ── Domain models (Firestore + RTDB serialization) ────────────────────────────
-keep class com.routex.app.auth.domain.model.**    { *; }
-keep class com.routex.app.student.domain.model.** { *; }
-keep class com.routex.app.admin.domain.model.**   { *; }
-keep class com.routex.app.maps.domain.model.**    { *; }
-keep class com.routex.app.driver.domain.**        { *; }
-keep class com.routex.app.eta.domain.model.**     { *; }

# ── Background services and receivers ────────────────────────────────────────
-keep class com.routex.app.maps.data.geofencing.** { *; }
-keep class com.routex.app.driver.data.service.**  { *; }
-keep class com.routex.app.core.notification.**    { *; }

# ── Navigation Compose ────────────────────────────────────────────────────────
-keepnames class androidx.navigation.** { *; }

# ── Jetpack Compose ───────────────────────────────────────────────────────────
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# ── DataStore ─────────────────────────────────────────────────────────────────
-keep class androidx.datastore.** { *; }

# ── Google Maps ───────────────────────────────────────────────────────────────
-keep class com.google.maps.android.** { *; }

# ── Remove debug logging in release ──────────────────────────────────────────
-assumenosideeffects class android.util.Log {
    public static int d(...);
    public static int v(...);
    public static int i(...);
}

# ── Prevent stripping of enum classes used in when-expressions ────────────────
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
