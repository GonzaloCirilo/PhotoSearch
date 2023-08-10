package com.example.photosearch.main_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    data class MainScreenState(
        val photoCards: ImmutableList<PhotoCardContentData>,
        val searchInput: String = "",
        val loadingContent: Boolean = false,
        val showingSearchResults: Boolean = false,
        val showingSearch: Boolean = false,
    ) {
        fun getScreenTitle(): String {
            return when {
                showingSearchResults.not() -> "Trending Now On Flickr"
                showingSearchResults && photoCards.size > 0 -> "Search Results for \"$searchInput\""
                else -> "No"
            }
        }
    }

    private var _state = MutableStateFlow(
        MainScreenState(
            photoCards = List(40) {
                PhotoCardContentData(
                    "https://p1.pxfuel.com/preview/402/676/56/shiba-dog-puppy-cute-japanese-inu.jpg",
                    "Title $it",
                    "Subtitle of photo $it"
                )
            }.toImmutableList()
        )
    )
    val state = _state.asStateFlow()


    fun onSearchSubmitted(query: String) {
        _state.value = _state.value.copy(searchInput = query, showingSearch = false, showingSearchResults = true)
    }

    fun showSearchScreen() {
        _state.value = _state.value.copy(showingSearch = true)
    }

    fun closeSearchScreen() {
        _state.value = _state.value.copy(showingSearch = false)
    }

}