package com.example.jetpackwithjunit.retrofit

import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.cache.CacheInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

        val retrofitClient by lazy {
            Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

    // Add the interceptor to OkHttpClient

    // Define the interceptor, add authentication headers
    var interceptor = Interceptor { chain ->
        val newRequest: Request = chain.request()
        val url = newRequest.url()
        println("********************Start**************************")
        Log.d("Request", ": " + newRequest)
        Log.d("Url", ": " + url)
        chain.proceed(newRequest)
    }
    val okHttpClient : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }

}