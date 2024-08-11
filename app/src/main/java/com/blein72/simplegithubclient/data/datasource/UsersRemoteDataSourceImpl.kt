package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.datasource.api.UserApi
import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import retrofit2.Response

class UsersRemoteDataSourceImpl(
    private val api: UserApi
) : UsersRemoteDataSource {

    override suspend fun getUsersList(): Response<List<UserResponseObject>> {
        return api.getUsersList()
    }

    override suspend fun getUserDetails(userName: String): Response<UserDetailResponseObject> {
        return api.getUserDetails(userName)
    }
}