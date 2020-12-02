package com.example.jetpackwithjunit.utils

object ProjectModules {
    const val core = ":core"
    const val onboarding = ":onboarding"
    const val conversationalBanking = ":conversational-banking"
    const val authToken = ":auth-token"
    const val utils = ":utils"
    const val testCommon = ":test-common"
    const val tsbComponents = ":tsb-components"
}

object AndroidSettings {
    const val compileSdk = 29
    const val minSdk = 19
    const val targetSdk = 29
}

object Versions {
    const val kotlin = "1.3.61"

    const val activityKtx = "1.1.0"
    const val androidxTest = "1.3.0"
    const val androidJunit5TestRunner = "1.2.0"
    const val androidxJunit = "1.1.2"
    const val archCoreKtx = "2.1.0"
    const val appCompat = "1.2.0"
    const val browser = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.3.1"
    const val dagger = "2.16"
    const val eventBus = "3.2.0"
    const val espresso = "3.2.0"
    const val fragment = "1.2.5"
    const val fragmentKtx = "1.2.5"
    const val gradle = "3.5.1"
    const val junit5 = "5.6.2"
    const val kotlinxSerialization = "0.20.0"
    const val libPhoneNumber = "8.12.9"
    const val lifecycleReactiveStreamsKtx = "2.2.0"
    const val livedataTesting = "1.1.2"
    const val livePerson = "5.1.1"
    const val lottie = "3.4.2"
    const val materialComponents = "1.2.1"
    const val mockk = "1.10.0"
    const val navigation = "2.3.0"
    const val okhttp = "4.8.1"
    const val onfidoCaptureSdk = "7.1.0" // SCO2A-751 we have experienced build issues with 7.2.0 and 7.3.0
    const val pahoMQTTV3 = "1.2.2"
    const val recyclerView = "1.1.0"
    const val retrofit = "2.9.0"
    const val retrofitGsonSerializationConverter = "2.7.1"
    const val rxjava = "2.1.1"
    const val rxkotlin = "2.4.0"
    const val rxbinding = "3.1.0"
    const val timber = "4.7.1"
    const val vectorDrawable = "1.1.0"
}

object Dependencies {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    const val phoneNumber = "com.googlecode.libphonenumber:libphonenumber:${Versions.libPhoneNumber}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    object Onfido {
        const val onfidoCaptureSdk = "com.onfido.sdk.capture:onfido-capture-sdk:${Versions.onfidoCaptureSdk}"
        const val onfidoCaptureSdkCore = "com.onfido.sdk.capture:onfido-capture-sdk-core:${Versions.onfidoCaptureSdk}"
    }

    const val paho = "org.eclipse.paho:org.eclipse.paho.client.mqttv3:${Versions.pahoMQTTV3}"

    const val livePerson = "com.liveperson.android:lp_messaging_sdk:${Versions.livePerson}"

    object KotlinX {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}"
    }

    object AndroidX {
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val lifecycleReactiveStreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycleReactiveStreamsKtx}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawable}"
        const val browser = "androidx.browser:browser:${Versions.browser}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val rxjava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val gsonSerializationConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonSerializationConverter}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Rx {
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
        const val java = "io.reactivex.rxjava2:rxandroid:${Versions.rxjava}"
        const val binding = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxbinding}"
    }

    const val eventBus = "org.greenrobot:eventbus:${Versions.eventBus}"
}

object TestDependencies {
    const val livedataTesting = "com.jraska.livedata:testing-ktx:${Versions.livedataTesting}"

    object JUnit {
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
        const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
        const val androidJunit5TestRunner = "de.mannodermaus.junit5:android-test-runner:${Versions.androidJunit5TestRunner}"
    }

    object AndroidX {
        const val archCoreKtx = "androidx.arch.core:core-testing:${Versions.archCoreKtx}"
        const val core = "androidx.test:core:${Versions.androidxTest}"
        const val coreKtx = "androidx.test:core-ktx:${Versions.androidxTest}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"
        const val junit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val rules = "androidx.test:rules:${Versions.androidxTest}"
        const val runner = "androidx.test:runner:${Versions.androidxTest}"
    }

    object Mockk {
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    }

    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit5:${Versions.kotlin}"
}
