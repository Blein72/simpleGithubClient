package com.blein72.simplegithubclient.data.datasource.local

import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.data.model.UserDetailData

interface UserLocalDataSource {

    suspend fun saveUsersList(data: List<UserData>)

    suspend fun getUserData(startingId: Int): List<UserData>

    suspend fun saveUserDetailData(data: UserDetailData)

    suspend fun getUserDetailData(name: String) : UserDetailData?
}