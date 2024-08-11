package com.blein72.simplegithubclient.data

import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.data.model.UserDetailData
import com.blein72.simplegithubclient.util.Result

interface UsersRepository {

    suspend fun getUsers(): Result<List<UserData>>
    suspend fun getUserDetails(name: String): Result<UserDetailData>
}