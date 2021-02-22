package com.example.jetpackwithjunit.retrofit

import com.example.jetpackwithjunit.model.Repos
import com.example.jetpackwithjunit.model.User
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("users")
  fun getAllUser(): Single<List<User>>

  @GET("users/{id}")
  fun getUserById(@Path("id") id : Int) : Single<User>


    //https://jsonplaceholder.typicode.com/users
}