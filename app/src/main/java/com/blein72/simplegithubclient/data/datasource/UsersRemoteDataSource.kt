package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail
import retrofit2.Response

interface UsersRemoteDataSource {

    suspend fun getUsersList(): Response<List<User>>
    suspend fun getUserDetails(userName: String): Response<UserDetail>
}