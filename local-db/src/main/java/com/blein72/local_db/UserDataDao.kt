package com.blein72.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blein72.core.util.USERS_PER_PAGE
import com.blein72.local_db.entity.UserDataDetailEntity
import com.blein72.local_db.entity.UserDataEntity

@Dao
interface UserDataDao {

    @Query("SELECT * FROM user_entity_table WHERE user_id > :startingId ORDER BY user_id ASC LIMIT :limit")
    suspend fun getNextUserData(startingId: Int, limit: Int = USERS_PER_PAGE): List<UserDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserData(data: List<UserDataEntity>)

    @Query("DELETE FROM user_entity_table")
    suspend fun deleteAllUsers()

    @Insert
    suspend fun saveUserDetail(userDataDetailEntity: UserDataDetailEntity)

    @Query("SELECT * FROM user_detail_entity_table WHERE login =:name")
    suspend fun getUserDetail(name: String): UserDataDetailEntity?
}