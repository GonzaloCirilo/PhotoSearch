package com.example.domain

import com.example.domain.faker.PhotoPageFaker
import com.example.domain.mock.PhotoRepositoryMock
import com.example.domain.use_case.SearchPhotosUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class SearchPhotosUseCaseTest {

    private val repository = PhotoRepositoryMock()

    private val searchPhotosUseCase by lazy {
        SearchPhotosUseCaseImpl(repository)
    }

    @Test
    fun `given a search query, pageSize and pageNumber when execute then expect valid page data`() =
        runTest {
            // given
            val searchQuery = "Cat"
            val pageSize = 20
            val currentPageNumber = 1
            val photoTitle = "BrownCat"
            val subTitle = "2022"
            val photoUrl = "photoUrl"
            repository.mock { _, _, _ ->
                PhotoPageFaker.createFakePhotoPage(
                    pageNumber = currentPageNumber,
                    nextPage = currentPageNumber + 1,
                    photos = listOf(
                        PhotoPageFaker.createFakePhoto(
                            title = photoTitle,
                            subtitle = subTitle,
                            photoUrl = photoUrl
                        )
                    )
                )
            }
            // when
            val result = searchPhotosUseCase.execute(searchQuery, pageSize, currentPageNumber)
            // then
            Assert.assertEquals(currentPageNumber, result.pageNumber)
            Assert.assertEquals(currentPageNumber + 1, result.nextPage)
            Assert.assertEquals(photoTitle, result.photos[0].title)
            Assert.assertEquals(subTitle, result.photos[0].subtitle)
            Assert.assertEquals(photoUrl, result.photos[0].photoUrl)
        }
}