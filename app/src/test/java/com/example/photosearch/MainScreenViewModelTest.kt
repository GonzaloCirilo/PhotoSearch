package com.example.photosearch

import com.example.domain.model.PhotoPage
import com.example.photosearch.fake.PhotoPageFaker
import com.example.photosearch.main_screen.MainScreenViewModel
import com.example.photosearch.mock.GetTrendingPhotosUseCaseMock
import com.example.photosearch.mock.SearchPhotosUseCaseMock
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MainScreenViewModelTest {


    private val getTrendingPhotosUseCaseImpl by lazy {
        GetTrendingPhotosUseCaseMock()
    }

    private val searchPhotosUseCase by lazy {
        SearchPhotosUseCaseMock()
    }

    private val viewModel by lazy {
        MainScreenViewModel(
            getTrendingPhotosUseCaseImpl,
            searchPhotosUseCase
        )
    }

    @Test
    fun `given initial screen state when onSearchSubmitted then update state and photos correctly`() =
        runTest {
            // given
            searchPhotosUseCase.mock(
                searchPhotosUseCaseMock = {
                    PhotoPage(
                        photos = listOf(PhotoPageFaker.createFakePhoto()),
                        pageNumber = 1,
                        nextPage = null,
                    )
                }
            )
            // when
            viewModel.onSearchSubmitted("Hello World")
            // then
            with(viewModel.state.value) {
                Assert.assertEquals("Hello World", searchInput)
                Assert.assertEquals(false, showingSearch)
                Assert.assertEquals(true, showingSearchResults)
            }
        }

    @Test
    fun `given initial screen state when showSearchScreen then update state correctly`() = runTest {
        // given / when
        viewModel.showSearchScreen()
        // then
        with(viewModel.state.value) {
            Assert.assertEquals("", searchInput)
            Assert.assertEquals(true, showingSearch)
            Assert.assertEquals(false, showingSearchResults)
        }
    }

    @Test
    fun `given the user is in the search screen when closeSearchScreen then update state correctly`() =
        runTest {
            // given / when
            viewModel.closeSearchScreen()
            // then
            with(viewModel.state.value) {
                Assert.assertEquals("", searchInput)
                Assert.assertEquals(false, showingSearch)
                Assert.assertEquals(false, showingSearchResults)
            }
        }
}