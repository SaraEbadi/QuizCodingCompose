package com.saraebadi.quizcodingcompose.data.di

import com.saraebadi.quizcodingcompose.data.network.ApiService
import com.saraebadi.quizcodingcompose.data.datasource.ApiDataSource
import com.saraebadi.quizcodingcompose.data.datasource.ApiDataSourceImp
import com.saraebadi.quizcodingcompose.data.repository.RepositoryImp
import com.saraebadi.quizcodingcompose.domin.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    internal fun provideApiDataSource(
        apiService: ApiService
    ): ApiDataSource {
        return ApiDataSourceImp(apiService)
    }

    @Provides
    @Singleton
    internal fun provideRepository(
        apiDataSource: ApiDataSource
    ): Repository {
        return RepositoryImp(apiDataSource)
    }
}