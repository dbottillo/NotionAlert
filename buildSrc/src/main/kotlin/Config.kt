import org.gradle.api.artifacts.dsl.DependencyHandler

object Config {
    object Libs {
        val core = listOf(
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}",
            "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        )
    }

    object Android {
        const val applicationId = "com.dbottillo.notionalert"
        const val compileSdk = 31
        const val buildTools = "31.0.0"
        const val minSDk = 26
        const val targetSdk = 31
        const val versionCode = 1
        const val versionName = "0.0.1"
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.core() {
    Config.Libs.core.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.di() {
    add("implementation", "com.google.dagger:hilt-android:${Versions.dagger}")
    add("implementation", "androidx.hilt:hilt-common:${Versions.hilt}")
    add("kapt", "com.google.dagger:hilt-compiler:${Versions.dagger}")
    add("implementation", "androidx.hilt:hilt-work:${Versions.hilt}")
    add("kapt", "androidx.hilt:hilt-compiler:${Versions.hilt}")
}

fun DependencyHandler.test() {
    add("testImplementation", "org.junit.jupiter:junit-jupiter-api:${Versions.junit}")
    add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}")
    add("testImplementation", "org.junit.jupiter:junit-jupiter-params:${Versions.junit}")
    add("testImplementation", "org.mockito:mockito-core:${Versions.mockito}")
    add("testImplementation", "org.mockito:mockito-inline:${Versions.mockito}")
    add("testImplementation", "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin}")
    add("testImplementation", "com.google.truth:truth:${Versions.truth}")
    add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
}

fun DependencyHandler.testAndroid() {
    add("testImplementation", "org.mockito:mockito-android:${Versions.mockito_android}")
}

fun DependencyHandler.network() {
    add("implementation", "com.squareup.okhttp3:okhttp:${Versions.OkHttp.core}")
    add("implementation", "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp.logging}")
    add("implementation", "com.squareup.retrofit2:retrofit:${Versions.Retrofit.core}")
    add("implementation", "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit.moshi}")
    add("implementation","com.squareup.moshi:moshi:${Versions.moshi}")
    add("kapt", "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}")
}

fun DependencyHandler.ui() {
    add("implementation", "androidx.core:core:${Versions.AndroidX.core}")
    add("implementation", "androidx.core:core-ktx:${Versions.AndroidX.core}")
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    add("implementation", "androidx.appcompat:appcompat:${Versions.AndroidX.compat}")
    add("implementation", "androidx.cardview:cardview:${Versions.AndroidX.cardview}")
    add("implementation", "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerview}")
    add("implementation", "com.google.android.material:material:${Versions.material}")
    add("implementation", "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}")
    add("implementation", "androidx.preference:preference:${Versions.AndroidX.preference}")
    add("implementation", "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}")
    add("implementation", "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}")
    add("implementation", "androidx.datastore:datastore:${Versions.AndroidX.dataStore}")
    add("implementation", "androidx.datastore:datastore-preferences:${Versions.AndroidX.dataStore}")
    add("implementation", "androidx.datastore:datastore-core:${Versions.AndroidX.dataStore}")
    add("implementation", "com.google.protobuf:protobuf-javalite:3.19.1")
}

fun DependencyHandler.lifecycle() {
    add("implementation", "androidx.lifecycle:lifecycle-runtime:${Versions.AndroidX.lifecycle}")
    add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}")
    add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}")
    add("implementation", "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.lifecycle}")
    add("implementation", "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidX.lifecycleExtensions}")
    add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}")
}

fun DependencyHandler.debug(){
    add("debugImplementation", "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}")
}

fun DependencyHandler.workManager(){
    add("implementation", "androidx.work:work-runtime:${Versions.workManager}")
    add("implementation", "androidx.work:work-runtime-ktx:${Versions.workManager}")
}