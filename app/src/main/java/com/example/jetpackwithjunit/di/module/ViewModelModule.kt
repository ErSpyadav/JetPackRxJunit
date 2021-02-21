package com.example.jetpackwithjunit.di.module

import androidx.lifecycle.ViewModel
import com.example.jetpackwithjunit.di.ViewModelKey
import com.example.jetpackwithjunit.viewmodel.DashboardFragmentViewModel
import com.example.jetpackwithjunit.viewmodel.LoginFragmentViewModel
import com.example.jetpackwithjunit.viewmodel.MPinFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun binLoginFragmentViewModel(viewModel: LoginFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MPinFragmentViewModel::class)
    internal abstract fun bindMPinFragmentViewModel(viewModel: MPinFragmentViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardFragmentViewModel::class)
    abstract fun bindDashboardFragmentViewModel(viewModel: DashboardFragmentViewModel):ViewModel

}