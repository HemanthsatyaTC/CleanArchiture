plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.hemanth.cleanarchiture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hemanth.cleanarchiture"
        minSdk = 24
        targetSdk = 34
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            var includeAndroidResources = true
        }
    }
}

dependencies {
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.glide)
    // Dependency Injection using Hilt
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-android-compiler:2.46")

    // Navigation component
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.compose.material:material:1.6.8")
    api ("com.google.android.material:material:1.1.0-alpha06")
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)

    implementation("io.coil-kt:coil-compose:2.0.0")
    //room dependencies
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.5-alpha")

    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("junit:junit:4.13.2")
    // For ViewModel testing
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    // UI Testing (Optional for Compose UI Tests)
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.4.3")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.3")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.4.3")
}