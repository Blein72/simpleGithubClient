package com.blein72.simplegithubclient.di

import com.blein72.simplegithubclient.data.UsersRepository
import com.blein72.simplegithubclient.data.UsersRepositoryImpl
import com.blein72.simplegithubclient.data.datasource.local.UserLocalDataSource
import com.blein72.simplegithubclient.data.datasource.local.UserLocalDataSourceImpl
import com.blein72.simplegithubclient.data.datasource.local.db.UserDataDao
import com.blein72.simplegithubclient.data.datasource.remote.api.UserApi
import com.blein72.simplegithubclient.data.datasource.remote.UsersRemoteDataSource
import com.blein72.simplegithubclient.data.datasource.remote.UsersRemoteDataSourceImpl
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