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
    compileSdk = libs.versions.build.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.agcoding.moviesjetpack"
        minSdk = libs.versions.build.min.sdk.get().toInt()
        targetSdk = libs.versions.build.compile.sdk.get().toInt()
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
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.get().toInt())
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
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

    // Core modules
    implementation(project(":core:shared"))
    implementation(project(":splash"))

    // Network modules
    implementation(project(":core:network:impl"))
    implementation(project(":core:network:public"))

    // Storage modules
    implementation(project(":core:storage:impl"))
    implementation(project(":core:storage:public"))

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

    // Logging
    implementation(libs.timber)

    // Testing
    testImplementation(libs.paging.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.unitTesting)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Splash screen
    implementation(libs.androidx.splash.screen)
}