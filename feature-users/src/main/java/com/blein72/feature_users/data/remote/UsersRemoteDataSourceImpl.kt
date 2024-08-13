package com.blein72.feature_users.data.remote

import com.blein72.core.data.response.UserDetailResponseObject
import com.blein72.core.data.response.UserResponseObject
import com.blein72.feature_users.data.remote.api.UserApi
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