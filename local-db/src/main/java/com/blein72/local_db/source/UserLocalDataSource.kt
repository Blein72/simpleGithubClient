package com.blein72.local_db.source

import com.blein72.core.data.UserData
import com.blein72.core.data.UserDetailData

interface UserLocalDataSource {

    suspend fun saveUsersList(data: List<UserData>)

    suspend fun getUserData(startingId: Int): List<UserData>

    suspend fun saveUserDetailData(data: UserDetailData)

    suspend fun getUserDetailData(name: String) : UserDetailData?
}