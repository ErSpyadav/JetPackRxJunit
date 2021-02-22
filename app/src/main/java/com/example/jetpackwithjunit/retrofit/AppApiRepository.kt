package com.example.jetpackwithjunit.retrofit

import com.example.jetpackwithjunit.model.User
import io.reactivex.Maybe
import io.reactivex.Single

interface AppApiRepository {
    fun getUserById(id : Int) : Single<User>
    fun getAllUser() : Single<List<User>>
}