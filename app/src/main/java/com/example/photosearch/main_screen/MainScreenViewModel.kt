package com.example.photosearch.main_screen

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel: ViewModel() {

    data class MainScreenState(
        val photoCards: ImmutableList<PhotoCardContentData>,
        val searchInput: String = "",
        val loadingContent: Boolean = false,
        val showingSearchResults: Boolean = false
    )

    private var _state = MutableStateFlow(
        MainScreenState(
        photoCards = List(40) { PhotoCardContentData("", "Title $it", "Subtitle of photo $it") }.toImmutableList()
    )
    )
    val state = _state.asStateFlow()

}