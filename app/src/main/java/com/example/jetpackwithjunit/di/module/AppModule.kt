package com.example.jetpackwithjunit.di.module

import dagger.Module

/**
 * Include others modules on top of AppModule
 */

@Module(includes = [ViewModelModule::class,RetrofitModule::class,PreferenceConnectorModule::class])
open class AppModule {
}