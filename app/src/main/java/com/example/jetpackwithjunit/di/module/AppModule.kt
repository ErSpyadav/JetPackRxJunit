package com.example.jetpackwithjunit.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Include others modules on top of AppModule
 */

@InstallIn(SingletonComponent::class)
@Module(includes = [RetrofitModule::class,PreferenceConnectorModule::class])
open class AppModule {
}
