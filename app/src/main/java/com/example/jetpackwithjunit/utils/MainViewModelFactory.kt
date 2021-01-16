package com.example.jetpackwithjunit.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackwithjunit.retrofit.ApiService
import com.example.jetpackwithjunit.viewmodel.FirstFragmentViewModel

class MainViewModelFactory(
    private val userService: ApiService
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java!!)) {
            return FirstFragmentViewModel(this.userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}