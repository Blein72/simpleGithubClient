package com.blein72.feature_users.data.remote

import com.blein72.core.data.response.UserDetailResponseObject
import com.blein72.core.data.response.UserResponseObject
import retrofit2.Response

interface UsersRemoteDataSource {

    suspend fun getUsersList(since:Int): Response<List<UserResponseObject>>
    suspend fun getUserDetails(userName: String): Response<UserDetailResponseObject>
}