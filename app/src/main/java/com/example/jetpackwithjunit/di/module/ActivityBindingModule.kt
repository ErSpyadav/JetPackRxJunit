package com.example.jetpackwithjunit.di.module

import com.example.jetpackwithjunit.di.DashBoardFragmentProvider
import com.example.jetpackwithjunit.views.DashBoradActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [DashBoardFragmentProvider::class])
    abstract fun bindDashboardActivity() : DashBoradActivity

}