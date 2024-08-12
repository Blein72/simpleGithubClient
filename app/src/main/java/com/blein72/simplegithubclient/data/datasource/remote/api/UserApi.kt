package com.blein72.simplegithubclient.data.datasource.remote.api

import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserResponseObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users?")
    suspend fun getUsersList(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): Response<List<UserResponseObject>>

    @GET("users/{user}")
    suspend fun getUserDetails(@Path("user") username: String): Response<UserDetailResponseObject>
}