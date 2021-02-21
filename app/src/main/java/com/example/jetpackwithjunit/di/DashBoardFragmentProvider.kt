package com.example.jetpackwithjunit.di

import com.example.jetpackwithjunit.DashboardFragment
import com.example.jetpackwithjunit.LoginFragment
import com.example.jetpackwithjunit.MPinFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface DashBoardFragmentProvider {

    @ContributesAndroidInjector
    fun provideLoginFragmentFactory() : LoginFragment

    @ContributesAndroidInjector
    fun provideMPinFragmentFactory() : MPinFragment

    @ContributesAndroidInjector
    fun provideDashBoardFragmentFactory() : DashboardFragment

}