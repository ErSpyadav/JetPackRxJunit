package com.example.jetpackwithjunit.di.component

import android.app.Application
import com.example.jetpackwithjunit.di.MyAppApplication
import com.example.jetpackwithjunit.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun module(module: AppModule): Builder

        fun appComponent(): AppComponent
    }

    fun inject(app: MyAppApplication)
}