package com.example.domain

import com.example.domain.faker.PhotoPageFaker
import com.example.domain.mock.PhotoRepositoryMock
import com.example.domain.use_case.GetTrendingPhotosUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetTrendingPhotosUseCaseTest {

    private val repository = PhotoRepositoryMock()

    private val getTrendingPhotosUseCase by lazy {
        GetTrendingPhotosUseCaseImpl(repository)
    }

    @Test
    fun `given a search query, pageSize and pageNumber when execute then expect valid page data`() =
        runTest {
            // given
            val pageSize = 20
            val currentPageNumber = 20
            val photoTitle = "Trending Photo 1"
            val subTitle = "2023"
            val photoUrl = "photoUrl"
            repository.mock(getTrendingPhotos = { _, _ ->
                PhotoPageFaker.createFakePhotoPage(
                    pageNumber = currentPageNumber,
                    nextPage = null,
                    photos = listOf(
                        PhotoPageFaker.createFakePhoto(
                            title = photoTitle,
                            subtitle = subTitle,
                            photoUrl = photoUrl
                        )
                    )
                )
            })
            // when
            val result = getTrendingPhotosUseCase.execute(pageSize, currentPageNumber)
            // then
            Assert.assertEquals(currentPageNumber, result.pageNumber)
            Assert.assertEquals(null, result.nextPage)
            Assert.assertEquals(photoTitle, result.photos[0].title)
            Assert.assertEquals(subTitle, result.photos[0].subtitle)
            Assert.assertEquals(photoUrl, result.photos[0].photoUrl)
        }
}