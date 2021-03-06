package com.example.jetpackwithjunit.di

import com.example.jetpackwithjunit.*
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

    @ContributesAndroidInjector
    fun provideTab1FragmentFactory() : Tab1Fragment

    @ContributesAndroidInjector
    fun provideTab2FragmentFactory() : Tab2Fragment

    @ContributesAndroidInjector
    fun provideTab3FragmentFactory() : Tab3Fragment


}