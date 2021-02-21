package com.example.jetpackwithjunit.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackwithjunit.retrofit.ApiService
import com.example.jetpackwithjunit.viewmodel.LoginFragmentViewModel

class MainViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginFragmentViewModel::class.java!!)) {
            return LoginFragmentViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}