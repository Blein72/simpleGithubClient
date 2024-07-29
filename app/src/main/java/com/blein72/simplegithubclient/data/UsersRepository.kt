package com.blein72.simplegithubclient.data

import com.blein72.simplegithubclient.data.model.User
import com.blein72.simplegithubclient.data.model.UserDetail
import com.blein72.simplegithubclient.util.Result

interface UsersRepository {

    suspend fun getUsers(): Result<List<User>>
    suspend fun getUserDetails(name: String): Result<UserDetail>
}