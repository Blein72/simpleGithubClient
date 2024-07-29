package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<User>>

    @GET("users/{user}")
    suspend fun getUserDetails(@Path("user") username: String): Response<UserDetail>
}