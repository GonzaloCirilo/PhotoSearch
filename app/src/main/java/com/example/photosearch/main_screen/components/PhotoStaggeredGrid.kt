package com.example.photosearch.main_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.photosearch.main_screen.MainComposableHeader
import com.example.photosearch.main_screen.PhotoCardContentData
import com.example.photosearch.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PhotoStaggeredGrid(
    screenTitle: String,
    photos: LazyPagingItems<PhotoCardContentData>,
    onSearchPressed: () -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.mainScreenPadding,
                start = Dimens.mainScreenPadding,
                end = Dimens.mainScreenPadding
            ),
        columns = StaggeredGridCells.Adaptive(minSize = 250.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalItemSpacing = 24.dp,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item(span = StaggeredGridItemSpan.FullLine ) {
            MainComposableHeader(
                screenTitle = screenTitle,
                onSearchPressed = onSearchPressed
            )
        }
        items(photos.itemCount) { index ->
            photos[index]?.let { PhotoCardItemContent(it, true) }
        }
        if (photos.loadState.refresh == LoadState.Loading || photos.loadState.append == LoadState.Loading) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}