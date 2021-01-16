package com.example.jetpackwithjunit.retrofit

import androidx.lifecycle.LiveData
import com.example.jetpackwithjunit.model.Repos
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    fun getRepositories(@Path("user") gitUser : String) : Maybe<List<Repos>>
}