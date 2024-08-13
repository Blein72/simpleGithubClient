package com.blein72.local_db.source

import com.blein72.core.data.UserData
import com.blein72.core.data.UserDetailData
import com.blein72.local_db.UserDataDao
import com.blein72.local_db.entity.toUserData
import com.blein72.local_db.entity.toUserDataEntity
import com.blein72.local_db.entity.toUserDetailData
import com.blein72.local_db.entity.toUserDetailEntity

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