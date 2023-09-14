plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rome.tech.mytraining"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rome.tech.mytraining"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
//    buildFeatures {
//        viewBinding = true
//        // dataBinding = true
//    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
// LiveDate
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
// Fragment
    implementation("androidx.fragment:fragment-ktx:1.6.1")
// Activity
    implementation("androidx.activity:activity-ktx:1.7.2")
    // Coroutine
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

//Picasso
    implementation("com.squareup.picasso:picasso:2.8")


// API
// https://mvnrepository.com/artifact/com.squareup.retrofit2/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// GSON
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}