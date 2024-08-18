plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("com.google.dagger.hilt.android") version "2.50"
    id("kotlin-kapt")
}
//def getAPI_KEy() {
//
//    Properties properties = new Properties()
//    properties.load(project.rootProject.file('local.properties').newDataInputStream())
//    return properties.getProperty("API_KEY")
//}
//def credentials = rootProject.file("local.properties")
//def credentialProperty = new Properties()
//credentialProperty.load(new FileInputStream(credentials))
android {
    namespace = "com.example.movie_feed"
    compileSdk = 34

    

    defaultConfig {
        applicationId = "com.example.movie_feed"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        buildConfigField("String", "TMDB_API_KEY", credentialProperty['TMDB_API_KEY'])
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material:1.3.0")

    //retrofit
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.paging.common.android)
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")
//    implementation("com.google.dagger:hilt-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")


    //paging
//    implementation("androidx.paging:paging-compose:1.0.0-alpha14")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.0.1")

    //lottie

    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation(libs.androidx.paging.compose.android)

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
    implementation("androidx.navigation:navigation-compose:2.5.2")
    implementation("androidx.core:core-splashscreen:1.0.0")




    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}