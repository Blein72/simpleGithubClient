package com.blein72.simplegithubclient.data.datasource

import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail
import retrofit2.Response

class UsersRemoteDataSourceImpl(
    private val api: UserApi
) : UsersRemoteDataSource {

    override suspend fun getUsersList(): Response<List<User>> {
        return api.getUsersList()
    }

    override suspend fun getUserDetails(userName: String): Response<UserDetail> {
        return api.getUserDetails(userName)
    }
}