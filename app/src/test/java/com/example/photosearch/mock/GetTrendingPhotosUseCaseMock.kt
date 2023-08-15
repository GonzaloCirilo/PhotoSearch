package com.example.photosearch.mock

import com.example.domain.model.PhotoPage
import com.example.domain.use_case.GetTrendingPhotosUseCase
import com.example.photosearch.fake.PhotoPageFaker

class GetTrendingPhotosUseCaseMock (
    private var searchPhotosUseCaseMock: () -> PhotoPage = { PhotoPageFaker.createFakePhotoPage() }
): GetTrendingPhotosUseCase {

    override suspend fun execute(pageSize: Int, pageNumber: Int): PhotoPage {
        return searchPhotosUseCaseMock()
    }

    fun mock(searchPhotosUseCaseMock: () -> PhotoPage) {
        this.searchPhotosUseCaseMock = searchPhotosUseCaseMock
    }

}