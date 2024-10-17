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
        versionCode = 2
        versionName = "1.2"

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
            buildConfigField("String", "IMAGE_URL_FULL", externalProperties["image_url_full"].toString())
        }
        debug {
            buildConfigField("String", "BASE_URL", externalProperties["base_url"].toString())
            buildConfigField("String", "ACCESS_TOKEN", externalProperties["access_token"].toString())
            buildConfigField("String", "IMAGE_URL", externalProperties["image_url"].toString())
            buildConfigField("String", "IMAGE_URL_FULL", externalProperties["image_url_full"].toString())
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v280)
    implementation(libs.androidx.activity.compose.v190)
    implementation(platform(libs.androidx.compose.bom.v20240500))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // For loading and tinting drawables on older versions of the platform
    implementation(libs.androidx.appcompat.resources)

    //Hilt - DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.material)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit.junit)
    testImplementation(libs.testng)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)

    // Navigation
    implementation(libs.androidx.navigation.compose.v277)
    implementation (libs.accompanist.systemuicontroller)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.compose)
    implementation(libs.lottie.compose)

    // Room components
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.0")
    implementation(libs.androidx.room.ktx)

    //Images
    implementation(libs.coil.compose)

    
}

kapt {
    correctErrorTypes = true
}




