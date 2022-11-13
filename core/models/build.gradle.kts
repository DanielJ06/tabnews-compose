@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.djr.tabnews.core.models"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core)
}