plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.agcoding.splash"
    compileSdk = libs.versions.build.compile.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.build.min.sdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    implementation(project(":core:shared"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Dagger - Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    // Logging
    implementation(libs.timber)

    // Splash screen
    implementation(libs.androidx.splash.screen)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.unitTesting)
}