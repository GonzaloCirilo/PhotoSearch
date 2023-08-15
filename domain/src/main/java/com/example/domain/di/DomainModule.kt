package com.example.domain.di

import com.example.domain.use_case.GetTrendingPhotosUseCase
import com.example.domain.use_case.GetTrendingPhotosUseCaseImpl
import com.example.domain.use_case.SearchPhotosUseCase
import com.example.domain.use_case.SearchPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DomainModule {

    @Binds
    fun providesGeTrendingPhotosUseCase (
        getTrendingPhotosUseCaseImpl: GetTrendingPhotosUseCaseImpl
    ): GetTrendingPhotosUseCase

    @Binds
    fun provideSearchPhotosUseCase (
        searchPhotosUseCaseImpl: SearchPhotosUseCaseImpl
    ): SearchPhotosUseCase

}