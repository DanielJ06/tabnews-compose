@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.serialization.plugin)
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
    implementation(libs.navigation.compose)
    implementation(libs.android.compose.material)
    implementation(libs.kotlin.serialization)
}