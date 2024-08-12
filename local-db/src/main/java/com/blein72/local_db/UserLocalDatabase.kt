package com.blein72.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blein72.local_db.entity.UserDataDetailEntity
import com.blein72.local_db.entity.UserDataEntity

@Database(entities = [UserDataEntity::class, UserDataDetailEntity::class], version = 2)
abstract class UserLocalDatabase: RoomDatabase() {

    abstract fun userDataDao(): UserDataDao
}