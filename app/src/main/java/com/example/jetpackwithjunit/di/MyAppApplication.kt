package com.example.jetpackwithjunit.di

import android.app.Application
import com.example.jetpackwithjunit.di.module.AppModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyAppApplication: Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this, AppModule())
    }
    override fun androidInjector() = dispatchingAndroidInjector
}

