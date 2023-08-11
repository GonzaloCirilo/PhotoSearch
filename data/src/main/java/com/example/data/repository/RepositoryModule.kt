package com.example.data.repository

import com.example.data.remote.PhotoSearchApi
import com.example.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(photoSearchApi: PhotoSearchApi): PhotoRepository =
        PhotoRepositoryImpl(photoSearchApi)
}