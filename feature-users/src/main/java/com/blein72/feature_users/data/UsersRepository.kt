package com.blein72.feature_users.data

import com.blein72.core.data.UserData
import com.blein72.core.data.UserDetailData
import com.blein72.core.util.Result

interface UsersRepository {

    suspend fun getUsers(latestId: Int): Result<List<UserData>>
    suspend fun getUserDetails(name: String): Result<UserDetailData>
}