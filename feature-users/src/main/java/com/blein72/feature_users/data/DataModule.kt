package com.blein72.feature_users.data

import com.blein72.feature_users.data.remote.UsersRemoteDataSource
import com.blein72.feature_users.data.remote.UsersRemoteDataSourceImpl
import com.blein72.feature_users.data.remote.api.UserApi
import com.blein72.local_db.UserDataDao
import com.blein72.local_db.source.UserLocalDataSource
import com.blein72.local_db.source.UserLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun providePharmacyRemoteDataSource(api: UserApi): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalDataStore(dao: UserDataDao): UserLocalDataSource {
        return UserLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: UsersRemoteDataSource,
        localDataSource: UserLocalDataSource
    ): UsersRepository {
        return UsersRepositoryImpl(dataSource, localDataSource)
    }


}