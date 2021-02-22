package com.example.jetpackwithjunit.di.module

import android.util.Log
import com.example.jetpackwithjunit.retrofit.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
       return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
       return Retrofit.Builder()
            .baseUrl(ApiUrl.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideInterceptor() : Interceptor {
        return Interceptor { chain ->
            val newRequest: Request = chain.request()
            val url = newRequest.url()
            println("********************Start**************************")
            Log.d("Request", ": " + newRequest)
            Log.d("Url", ": " + url)
            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideOkkHttp():OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(RetrofitClient.interceptor).build()

    }
    @Provides
    @Singleton
    fun provideSConnectApiRepository(apiHelper: AppApiRepositoryImpl): AppApiRepository = apiHelper

}