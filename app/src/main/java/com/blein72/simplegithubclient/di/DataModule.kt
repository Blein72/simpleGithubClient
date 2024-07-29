package com.blein72.simplegithubclient.di

import com.blein72.simplegithubclient.data.UsersRepository
import com.blein72.simplegithubclient.data.UsersRepositoryImpl
import com.blein72.simplegithubclient.data.datasource.UserApi
import com.blein72.simplegithubclient.data.datasource.UsersRemoteDataSource
import com.blein72.simplegithubclient.data.datasource.UsersRemoteDataSourceImpl
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
        return  UsersRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideRepository(dataSource:  UsersRemoteDataSource): UsersRepository {
        return  UsersRepositoryImpl(dataSource)
    }
}