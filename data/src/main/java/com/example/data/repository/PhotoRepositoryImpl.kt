package com.example.data.repository

import com.example.data.remote.PhotoSearchApi
import com.example.data.remote.dto.PhotoMapper
import com.example.domain.model.PhotoPage
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoSearchApi: PhotoSearchApi,
) : PhotoRepository {
    override suspend fun getTrendingPhotos(pageNumber: Int, pageSize: Int): PhotoPage {
        try {
            return PhotoMapper.map(
                photoSearchApi.getTrendingPhotos(
                    page = pageNumber,
                    itemsPerPage = pageSize
                ).photos
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun serachPhotos(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): PhotoPage {
        return PhotoMapper.map(
            photoSearchApi.searchPhotos(
                searchText = searchQuery,
                itemsPerPage = pageSize,
                page = pageNumber
            ).photos
        )
    }

}