package com.blein72.simplegithubclient.data.datasource.api

import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<UserResponseObject>>

    @GET("users/{user}")
    suspend fun getUserDetails(@Path("user") username: String): Response<UserDetailResponseObject>
}