package com.blein72.feature_users.data.remote.api

import com.blein72.core.data.response.UserDetailResponseObject
import com.blein72.core.data.response.UserResponseObject
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