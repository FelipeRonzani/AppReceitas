plugins {
    id("com.android.application") version libs.versions.agp
    id("org.jetbrains.kotlin.android") version libs.versions.kotlin
    id("kotlin-kapt")
}

android {
    namespace = "com.appreceitas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.appreceitas"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    // Core AndroidX Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.activity)

    // Kotlin Standard Library
    implementation(libs.kotlin.stdlib)

    // Room Dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    implementation(libs.androidx.room.common) // Room Runtime
    kapt("androidx.room:room-compiler:2.6.1") // Room Compiler (kapt)
    implementation("androidx.room:room-ktx:2.6.1")

    // Unit Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
