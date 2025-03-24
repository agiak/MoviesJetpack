plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.agcoding.moviesjetpack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.agcoding.moviesjetpack"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose - Material
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    // Navigation
    implementation(libs.bundles.navigation)

    // Dagger - Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    // Local database (Room)
    implementation(libs.bundles.room)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)

    // Networking - Ktor
    implementation(libs.bundles.ktor)
    implementation(libs.gson)

    // Paging
    implementation(libs.bundles.paging)

    // Loging
    implementation(libs.timber)

    // Image loading
    implementation(libs.bundles.coil)

    // Testing
    testImplementation(libs.paging.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.unitTesting)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Splash screen
    implementation(libs.androidx.splash.screen)
}