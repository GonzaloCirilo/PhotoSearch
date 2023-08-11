package com.example.data.remote

import com.example.data.remote.dto.PhotosWrapperDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoSearchApi {

    @GET("rest/")
    suspend fun getTrendingPhotos(
        // Disabling FlickerApiConstants.GET_RECENT_PHOTOS_METHOD to avoid inappropriate content
        // @Query("method") method: String = FlickerApiConstants.GET_RECENT_PHOTOS_METHOD,
        @Query("method") method: String = FlickerApiConstants.SEARCH_PHOTOS_METHOD,
        @Query("extras") extraInfo: String = "owner_name,date_upload",
        @Query("tags") tags: String = "animal", // Define a tag to avoid inappropriate content
        @Query("per_page") itemsPerPage: Int,
        @Query("page") page: Int,
    ): PhotosWrapperDto

    @GET("rest/")
    suspend fun searchPhotos(
        @Query("method") method: String = FlickerApiConstants.SEARCH_PHOTOS_METHOD,
        @Query("extras") extraInfo: String = "owner_name,date_upload",
        @Query("text") searchText: String,
        @Query("tags") tags:String,
        @Query("safe_search") safeSearch: Int = 1,
        @Query("per_page") itemsPerPage: Int,
        @Query("page") page: Int,
    ): PhotosWrapperDto


}