package com.blein72.local_db.di

import android.content.Context
import androidx.room.Room
import com.blein72.core.util.DATABASE_NAME
import com.blein72.local_db.UserDataDao
import com.blein72.local_db.UserLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserLocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserLocalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserLocalDatabase): UserDataDao {
        return database.userDataDao()
    }
}