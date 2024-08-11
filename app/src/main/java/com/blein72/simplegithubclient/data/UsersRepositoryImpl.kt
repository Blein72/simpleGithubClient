package com.blein72.simplegithubclient.data

import com.blein72.simplegithubclient.data.datasource.UsersRemoteDataSource
import com.blein72.simplegithubclient.data.datasource.api.response.UserResponseObject
import com.blein72.simplegithubclient.data.datasource.api.response.UserDetailResponseObject
import com.blein72.simplegithubclient.data.model.UserData
import com.blein72.simplegithubclient.data.model.UserDetailData
import com.blein72.simplegithubclient.data.model.toUserDataList
import com.blein72.simplegithubclient.data.model.toUserDetailData
import com.blein72.simplegithubclient.util.NoBodyException
import com.blein72.simplegithubclient.util.Result

class UsersRepositoryImpl(private val remoteDataSource: UsersRemoteDataSource): UsersRepository {

    override suspend fun getUsers(): Result<List<UserData>> {
       return try {
           val response = remoteDataSource.getUsersList()
           if (response.isSuccessful) {
               val usersList = response.body()
               if (usersList != null) {
                   Result.Success(usersList.toUserDataList())
               } else {
                   Result.Error(NoBodyException)
               }
           } else {
               Result.Error(Exception("Failed with code: ${response.code()}"))
           }
       } catch (e:Exception) {
            Result.Error(e)
       }
    }

    override suspend fun getUserDetails(name: String): Result<UserDetailData> {
        return try {
            val response = remoteDataSource.getUserDetails(name)
            if (response.isSuccessful) {
                val usersList = response.body()
                if (usersList != null) {
                    Result.Success(usersList.toUserDetailData())
                } else {
                    Result.Error(NoBodyException)
                }
            } else {
                Result.Error(Exception("Failed with code: ${response.code()}"))
            }
        } catch (e:Exception) {
            Result.Error(e)
        }
    }
}