package com.example.jetpackwithjunit.retrofit

import com.example.jetpackwithjunit.model.User
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiRepositoryImpl  @Inject constructor(private val apiService: ApiService) : AppApiRepository {
    override fun getUserById(id: Int): Single<User> {
       return apiService.getUserById(id)
    }

    override fun getAllUser(): Single<List<User>> {
        return apiService.getAllUser()
    }
}