package com.blein72.simplegithubclient.data.datasource.remote

import com.blein72.simplegithubclient.data.datasource.remote.api.UserApi
import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.datasource.remote.api.response.UserResponseObject
import retrofit2.Response

const val USERS_PER_PAGE = 50 // 50

class UsersRemoteDataSourceImpl(
    private val api: UserApi
) : UsersRemoteDataSource {

    override suspend fun getUsersList(since: Int): Response<List<UserResponseObject>> {
        return api.getUsersList(
            since = since,
            perPage = USERS_PER_PAGE
        )
    }

    override suspend fun getUserDetails(userName: String): Response<UserDetailResponseObject> {
        return api.getUserDetails(userName)
    }
}