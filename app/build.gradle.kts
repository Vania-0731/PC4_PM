plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sifuentes.vania.lab13"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sifuentes.vania.lab13"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    
    // Habilitar ViewBinding para generar clases de binding automáticamente
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Dependencias básicas de Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    
    // Dependencias de CameraX para funcionalidad de cámara
    val cameraxVersion = "1.0.1"
    implementation("androidx.camera:camera-camera2:$cameraxVersion") // Biblioteca principal de CameraX
    implementation("androidx.camera:camera-lifecycle:$cameraxVersion") // Integración con lifecycle
    implementation("androidx.camera:camera-view:1.0.0-alpha27") // Vista previa de cámara
    
    // Glide para cargar y mostrar imágenes
    implementation("com.github.bumptech.glide:glide:4.12.0")
    
    // Dependencias de testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}