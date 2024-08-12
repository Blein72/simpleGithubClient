package com.blein72.simplegithubclient.data.datasource.local

import com.blein72.simplegithubclient.data.datasource.local.db.UserDataDao
import com.blein72.simplegithubclient.data.datasource.local.db.entity.toUserData
import com.blein72.simplegithubclient.data.datasource.local.db.entity.toUserDataEntity
import com.blein72.simplegithubclient.data.datasource.local.db.entity.toUserDetailData
import com.blein72.simplegithubclient.data.datasource.local.db.entity.toUserDetailEntity
import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.data.model.UserDetailData

class UserLocalDataSourceImpl(
    private val dao: UserDataDao
) : UserLocalDataSource {

    override suspend  fun saveUsersList(data: List<UserData>) {
        dao.saveUserData(data.map { it.toUserDataEntity() })
    }

    override suspend  fun getUserData(startingId: Int): List<UserData> {
        return dao.getNextUserData(startingId).map { it.toUserData() }
    }

    override suspend fun saveUserDetailData(data: UserDetailData) {
        dao.saveUserDetail(data.toUserDetailEntity())
    }

    override suspend fun getUserDetailData(userId: String): UserDetailData? {
        return dao.getUserDetail(userId)?.toUserDetailData()
    }
}