package com.example.data.remote

import com.example.data.remote.dto.PhotosDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoSearchApi {

    @GET("/rest/")
    suspend fun getTrendingPhotos(
        @Query("method") method: String = FlickerApiConstants.GET_RECENT_PHOTOS_METHOD,
        @Query("extras") extraInfo: String = "owner_name",
        @Query("per_page") itemsPerPage: Int,
        @Query("page") page: Int,
    ): PhotosDto

    @GET("/rest/")
    suspend fun searchPhotos(
        @Query("method") method: String = FlickerApiConstants.SEARCH_PHOTOS_METHOD,
        @Query("extras") extraInfo: String = "owner_name",
        @Query("per_page") itemsPerPage: Int,
        @Query("page") page: Int,
        @Query("text") searchText: String,
    ): PhotosDto


}