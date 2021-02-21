package com.example.jetpackwithjunit.di.component

import android.app.Application
import com.example.jetpackwithjunit.di.MyAppApplication
import com.example.jetpackwithjunit.di.module.ActivityBindingModule
import com.example.jetpackwithjunit.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DaggerApplication

@Component(modules = [AndroidInjectionModule::class,ActivityBindingModule::class,AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        @BindsInstance
        fun appModule(appModule: AppModule) : Builder

        fun appComponent() : AppComponent
    }
    fun inject(app: MyAppApplication)
}