package com.example.data.di

import com.example.data.remote.FlickerApiConstants
import com.example.data.remote.PhotoSearchApi
import com.example.data.remote.PhotoSearchQueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providePhotoSearchApi(): PhotoSearchApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoSearchQueryInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(FlickerApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoSearchApi::class.java)
    }


}