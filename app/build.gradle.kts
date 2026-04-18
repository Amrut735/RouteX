plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.routex.app"
    //noinspection GradleDependency
    compileSdk = 34

    defaultConfig {
        applicationId = "com.Amrut.RouteX"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }

        // Inject Maps API key from local.properties
        val localPropertiesFile = rootProject.file("local.properties")
        val mapsApiKey = if (localPropertiesFile.exists()) {
            localPropertiesFile.readLines()
                .firstOrNull { it.startsWith("MAPS_API_KEY=") }
                ?.substringAfter("=") ?: ""
        } else ""
        buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
    }

    signingConfigs {
        // To generate a keystore, run:
        //   keytool -genkey -v -keystore routex-release.jks -keyalg RSA -keysize 2048
        //            -validity 10000 -alias routex
        // Then set these in local.properties:
        //   KEY_STORE_FILE=routex-release.jks
        //   KEY_STORE_PASSWORD=yourpassword
        //   KEY_ALIAS=routex
        //   KEY_PASSWORD=yourpassword
        create("release") {
            val storeFile  = (project.findProperty("KEY_STORE_FILE") as String?).orEmpty()
            val storePass  = (project.findProperty("KEY_STORE_PASSWORD") as String?).orEmpty()
            val keyAlias   = (project.findProperty("KEY_ALIAS") as String?).orEmpty()
            val keyPass    = (project.findProperty("KEY_PASSWORD") as String?).orEmpty()
            if (storeFile.isNotBlank()) {
                this.storeFile     = file(storeFile)
                this.storePassword = storePass
                this.keyAlias      = keyAlias
                this.keyPassword   = keyPass
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled    = true
            isShrinkResources  = true
            signingConfig      = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        // ── Hackathon demo build: shrunk + debuggable (no key needed) ──────
        create("hackathon") {
            initWith(getByName("release"))
            isDebuggable       = true          // allows adb install + logs
            signingConfig      = signingConfigs.getByName("debug")
            versionNameSuffix   = "-hackathon"
        }
        debug {
            isDebuggable = true
            versionNameSuffix   = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // OSMDroid (Free OpenStreetMap alternative to Google Maps)
    implementation("org.osmdroid:osmdroid-android:6.1.18")
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.database)
    implementation(libs.firebase.messaging)
    implementation(libs.play.services.auth)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Maps + Location + Geofencing
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)

    // Accompanist
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.permissions)

    // Debug
    debugImplementation(libs.androidx.ui.tooling)
}

kapt {
    correctErrorTypes = true
}
