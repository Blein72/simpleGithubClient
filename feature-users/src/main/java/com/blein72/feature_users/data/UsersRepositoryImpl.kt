package com.blein72.feature_users.data

import com.blein72.core.data.UserData
import com.blein72.core.data.UserDetailData
import com.blein72.core.data.toUserDataList
import com.blein72.core.data.toUserDetailData
import com.blein72.core.util.NoBodyException
import com.blein72.feature_users.data.remote.UsersRemoteDataSource
import com.blein72.local_db.source.UserLocalDataSource
import com.blein72.core.util.Result

class UsersRepositoryImpl(
    private val remoteDataSource: UsersRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UsersRepository {

    override suspend fun getUsers(latestId: Int): Result<List<UserData>> {
        val localData = localDataSource.getUserData(startingId = latestId)
        if (localData.isNotEmpty()) {
            return Result.Success(localData)
        } else {
            return try {
                val response = remoteDataSource.getUsersList(since = latestId)
                if (response.isSuccessful) {
                    val usersList = response.body()
                    if (usersList != null) {
                        val result = usersList.toUserDataList()
                        localDataSource.saveUsersList(result)
                        Result.Success(result)
                    } else {
                        Result.Error(NoBodyException)
                    }
                } else {
                    Result.Error(Exception("Failed with code: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getUserDetails(name: String): Result<UserDetailData> {
        val userdata = localDataSource.getUserDetailData(name)
        if (userdata != null) {
            return Result.Success(userdata)
        } else {
            return try {
                val response = remoteDataSource.getUserDetails(name)
                if (response.isSuccessful) {
                    val userDetail = response.body()
                    if (userDetail != null) {
                        localDataSource.saveUserDetailData(userDetail.toUserDetailData())
                        Result.Success(userDetail.toUserDetailData())
                    } else {
                        Result.Error(NoBodyException)
                    }
                } else {
                    Result.Error(Exception("Failed with code: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}