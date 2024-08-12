package com.blein72.simplegithubclient.data.datasource.remote

import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserResponseObject
import retrofit2.Response

interface UsersRemoteDataSource {

    suspend fun getUsersList(since:Int): Response<List<UserResponseObject>>
    suspend fun getUserDetails(userName: String): Response<UserDetailResponseObject>
}