package com.blein72.simplegithubclient.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blein72.simplegithubclient.data.datasource.local.db.entity.UserDataDetailEntity
import com.blein72.simplegithubclient.data.datasource.local.db.entity.UserDataEntity

@Database(entities = [UserDataEntity::class, UserDataDetailEntity::class], version = 2)
abstract class UserLocalDatabase: RoomDatabase() {

    abstract fun userDataDao(): UserDataDao
}