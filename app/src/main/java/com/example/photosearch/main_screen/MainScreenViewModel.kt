package com.example.photosearch.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.use_case.GetTrendingPhotosUseCase
import com.example.domain.use_case.SearchPhotosUseCase
import com.example.photosearch.paging.PagingConfiguration
import com.example.photosearch.paging.SearchPhotosPagingSource
import com.example.photosearch.paging.TrendingPhotosPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTrendingPhotosUseCase: GetTrendingPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
) : ViewModel() {

    data class MainScreenState(
        val searchInput: String = "",
        val loadingContent: Boolean = false,
        val showingSearchResults: Boolean = false,
        val showingSearch: Boolean = false,
    )

    var pagedPhotoDataStream: Flow<PagingData<PhotoCardContentData>> = Pager(
        config = PagingConfig(pageSize = PagingConfiguration.PAGE_SIZE),
    ) {
        TrendingPhotosPagingSource(getTrendingPhotosUseCase)
    }.flow.cachedIn(viewModelScope).map { pg ->
        pg.map {
            PhotoCardContentData(
                imageUrl = it.photoUrl,
                title = it.title,
                subtitle = it.subtitle
            )
        }
    }
        private set

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


    fun onSearchSubmitted(query: String) {
        _state.value = _state.value.copy(
            searchInput = query,
            showingSearch = false,
            showingSearchResults = true
        )
        pagedPhotoDataStream = Pager(
            config = PagingConfig(pageSize = PagingConfiguration.PAGE_SIZE),
        ) {
            SearchPhotosPagingSource(searchPhotosUseCase, query)
        }.flow.cachedIn(viewModelScope).map { pg ->
            pg.map {
                PhotoCardContentData(
                    imageUrl = it.photoUrl,
                    title = it.title,
                    subtitle = it.subtitle
                )
            }
        }
    }

    fun showSearchScreen() {
        _state.value = _state.value.copy(showingSearch = true)
    }

    fun closeSearchScreen() {
        _state.value = _state.value.copy(showingSearch = false)
    }

}