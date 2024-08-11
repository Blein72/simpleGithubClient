package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import retrofit2.Response

interface UsersRemoteDataSource {

    suspend fun getUsersList(): Response<List<UserResponseObject>>
    suspend fun getUserDetails(userName: String): Response<UserDetailResponseObject>
}