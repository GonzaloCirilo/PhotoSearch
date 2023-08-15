package com.example.photosearch.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.photosearch.main_screen.components.PhotoRegularGrid
import com.example.photosearch.main_screen.components.PhotoStaggeredGrid
import com.example.photosearch.theme.Aquamarine
import com.example.photosearch.theme.AquamarineDark
import com.example.photosearch.theme.Dimens
import com.example.photosearch.theme.Gray
import com.example.photosearch.theme.Gray700
import com.example.photosearch.theme.Gray800
import com.example.photosearch.theme.Typography
import kotlinx.coroutines.flow.flow

const val ASPECT_RATIO_16_9 = 1.7777f

@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel(), onSearchPressed: () -> Unit) {
    val screenState by viewModel.state.collectAsState()
    val photos = viewModel.pagedPhotoDataStream.collectAsLazyPagingItems()
    MainScreenContent(screenState, photos, onSearchPressed)
}

@Composable
fun MainScreenContent(
    state: MainScreenViewModel.MainScreenState,
    photos: LazyPagingItems<PhotoCardContentData>,
    onSearchPressed: () -> Unit
) {
    val screenTitle = when {
        state.showingSearchResults.not() -> "Trending Now On Flickr"
        state.showingSearchResults && (photos.itemCount > 0 || photos.loadState.refresh == LoadState.Loading) -> "Search Results for \"${state.searchInput}\""
        else -> "No search results for ${state.searchInput}"
    }
    val staggered = state.showingSearchResults

    if (staggered) {
        PhotoStaggeredGrid(screenTitle = screenTitle, photos = photos, onSearchPressed = onSearchPressed)
    } else {
        PhotoRegularGrid(screenTitle = screenTitle, photos = photos, onSearchPressed = onSearchPressed)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainComposableHeader(
    modifier: Modifier = Modifier,
    screenTitle: String,
    onSearchPressed: () -> Unit
) {
    Column {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.size(64.dp),
                onClick = { onSearchPressed() },
                colors = ButtonDefaults.colors(
                    containerColor = Aquamarine,
                    pressedContainerColor = AquamarineDark,
                    focusedContainerColor = AquamarineDark
                )
            ) {
                Icon(
                    modifier = Modifier.size(44.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Gray
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Column {
                Text(
                    text = "PHOTO SEARCH",
                    style = Typography.h1.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = Typography.subtitle.fontSize)) {
                            withStyle(style = SpanStyle(color = Gray800)) {
                                append("powered by ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Gray700,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 32.sp
                                )
                            ) {
                                append("flickr")
                            }
                        }
                    }
                )
            }
        }
        Text(
            style = Typography.subtitle,
            text = screenTitle,
            modifier = Modifier.padding(top = Dimens.subtitleTopPadding),
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(showBackground = true, widthDp = 1920, heightDp = 1080)
@Composable
fun MainScreenPreview() {
    val photos = flow<PagingData<PhotoCardContentData>> {
        PagingData.from(
            listOf(
                PhotoCardContentData(
                    imageUrl = "",
                    title = "",
                    subtitle = ""
                )
            )
        )
    }
    MaterialTheme {
        MainScreenContent(
            MainScreenViewModel.MainScreenState(),
            photos.collectAsLazyPagingItems()) {}
    }
}