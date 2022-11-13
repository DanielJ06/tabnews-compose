@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt.android.plugin)
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.djr.tabnews"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.version.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:models"))
    implementation(project(":core:commons"))
    implementation(project(":core:uikit"))
    implementation(project(":core:domain"))

    implementation(libs.androidx.core)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.runtime)
    implementation(libs.navigation.compose)
    implementation(libs.android.coroutines)

    implementation(libs.work.runtime)
    implementation(libs.accompanist)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.google.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation)

    implementation(libs.bundles.network)
}