package com.example.domain.repository

import com.example.domain.model.PhotoPage

interface PhotoRepository {

    /**
     * Returns paged data of trending photos
     * @param pageNumber Current pageNumber
     * @param pageSize Size of each page
     */
    suspend fun getTrendingPhotos(pageNumber: Int, pageSize: Int): PhotoPage

    /**
     * Returns paged data of photos by a search query
     * @param pageNumber Current pageNumber
     * @param pageSize Size of each page
     * @param searchQuery String containing the query to use to search photos
     */
    suspend fun serachPhotos(pageNumber: Int, pageSize: Int, searchQuery: String): PhotoPage
}