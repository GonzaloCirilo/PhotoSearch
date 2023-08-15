package com.example.domain.mock

import com.example.domain.faker.PhotoPageFaker
import com.example.domain.model.PhotoPage
import com.example.domain.repository.PhotoRepository


typealias SearchPhotosMethod = (pageNumber: Int, pageSize: Int, searchQuery: String) -> PhotoPage
typealias GetTrendingPhotos = (pageNumber: Int, pageSize: Int) -> PhotoPage

class PhotoRepositoryMock : PhotoRepository {

    private var getTrendingPhotosMock: GetTrendingPhotos =
        { pageNumber, _ -> PhotoPageFaker.createFakePhotoPage(pageNumber = pageNumber) }
    private var searchPhotosMock: SearchPhotosMethod =
        { pageNumber, _, _ -> PhotoPageFaker.createFakePhotoPage(pageNumber = pageNumber) }

    override suspend fun getTrendingPhotos(pageNumber: Int, pageSize: Int): PhotoPage {
        return getTrendingPhotosMock(pageNumber, pageSize)
    }

    override suspend fun searchPhotos(
        pageNumber: Int,
        pageSize: Int,
        searchQuery: String
    ): PhotoPage {
        return searchPhotosMock(pageNumber, pageSize, searchQuery)
    }

    fun mock(
        getTrendingPhotos: GetTrendingPhotos = this.getTrendingPhotosMock,
        searchPhotos: SearchPhotosMethod = this.searchPhotosMock
    ) {
        this.searchPhotosMock = searchPhotos
        this.getTrendingPhotosMock = getTrendingPhotos
    }


}