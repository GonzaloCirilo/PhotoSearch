package com.example.photosearch

import com.example.photosearch.main_screen.MainScreenViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MainScreenViewModelTest {

    @Test
    fun `given initial screen state when onSearchSubmitted then update state correctly`() = runTest {
        // given
        val viewModel = MainScreenViewModel()
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
        // given
        val viewModel = MainScreenViewModel()
        // when
        viewModel.showSearchScreen()
        // then
        with(viewModel.state.value) {
            Assert.assertEquals("", searchInput)
            Assert.assertEquals(true, showingSearch)
            Assert.assertEquals(false, showingSearchResults)
        }
    }

    @Test
    fun `given the user is in the search screen when closeSearchScreen then update state correctly`() = runTest {
        // given
        val viewModel = MainScreenViewModel()
        // when
        viewModel.closeSearchScreen()
        // then
        with(viewModel.state.value) {
            Assert.assertEquals("", searchInput)
            Assert.assertEquals(false, showingSearch)
            Assert.assertEquals(false, showingSearchResults)
        }
    }

}