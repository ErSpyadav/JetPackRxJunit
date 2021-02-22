package com.example.jetpackwithjunit.repository

import com.example.jetpackwithjunit.model.User
import com.example.jetpackwithjunit.retrofit.AppApiRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MPinRepository @Inject constructor(private val apiRepository: AppApiRepository) {

    fun getUserById(id : Int) : Single<User>{
        return apiRepository.getUserById(id)
    }


}