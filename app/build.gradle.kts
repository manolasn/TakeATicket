import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val externalPropertiesFile = rootProject.file("external.properties")
val externalProperties = Properties()
externalProperties.load(FileInputStream(externalPropertiesFile))

android {
    namespace = "gr.manolasn.takeaticket"
    compileSdk = 34

    defaultConfig {
        applicationId = "gr.manolasn.takeaticket"
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", externalProperties["base_url"].toString())
            buildConfigField("String", "ACCESS_TOKEN", externalProperties["access_token"].toString())
            buildConfigField("String", "IMAGE_URL", externalProperties["image_url"].toString())
        }
        debug {
            buildConfigField("String", "BASE_URL", externalProperties["base_url"].toString())
            buildConfigField("String", "ACCESS_TOKEN", externalProperties["access_token"].toString())
            buildConfigField("String", "IMAGE_URL", externalProperties["image_url"].toString())
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // For loading and tinting drawables on older versions of the platform
    implementation("androidx.appcompat:appcompat-resources:1.6.1")

    //Hilt - DI
    implementation("com.google.dagger:hilt-android:2.50")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.google.android.material:material:1.12.0")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.32.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")

    //Preferences
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    implementation("com.github.bumptech.glide:compose:1.0.0-alpha.3")
    implementation("com.airbnb.android:lottie-compose:6.3.0")

    // Room components
    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")

    implementation("androidx.room:room-ktx:2.6.0")

}

kapt {
    correctErrorTypes = true
}




