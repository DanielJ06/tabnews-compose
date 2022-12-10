@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.lib)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android.plugin)
}

android {
    namespace = "com.djr.tabnews.core.database"
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
    implementation(project(":core:models"))
    implementation(project(":core:commons"))

    implementation(libs.androidx.core)
    implementation(libs.android.coroutines)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.google.hilt.compiler)
    implementation(libs.hilt.work)
}