package com.example.photosearch.mock

import com.example.domain.model.PhotoPage
import com.example.domain.use_case.SearchPhotosUseCase
import com.example.photosearch.fake.PhotoPageFaker

class SearchPhotosUseCaseMock(
    private var searchPhotosUseCaseMock: () -> PhotoPage = { PhotoPageFaker.createFakePhotoPage() }
): SearchPhotosUseCase {

    override suspend fun execute(query: String, pageSize: Int, pageNumber: Int): PhotoPage {
        return searchPhotosUseCaseMock()
    }

    fun mock(searchPhotosUseCaseMock: () -> PhotoPage) {
        this.searchPhotosUseCaseMock = searchPhotosUseCaseMock
    }

}