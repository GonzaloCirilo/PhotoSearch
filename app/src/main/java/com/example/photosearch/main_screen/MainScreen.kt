package com.example.photosearch.main_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.Border
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.photosearch.R
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

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainScreenContent(
    state: MainScreenViewModel.MainScreenState,
    photos: LazyPagingItems<PhotoCardContentData>,
    onSearchPressed: () -> Unit
) {
    val screenTitle = when {
        state.showingSearchResults.not() -> "Trending Now On Flickr"
        state.showingSearchResults && photos.itemCount > 0 -> "Search Results for \"${state.searchInput}\""
        else -> "No search results for ${state.searchInput}"
    }
    TvLazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.mainScreenPadding,
                start = Dimens.mainScreenPadding,
                end = Dimens.mainScreenPadding
            ),
        columns = TvGridCells.Adaptive(minSize = 250.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item(span = { TvGridItemSpan(maxLineSpan) }) {
            MainComposableHeader(
                screenTitle = screenTitle,
                onSearchPressed = onSearchPressed
            )
        }
        items(photos.itemCount) { index ->
            photos[index]?.let { PhotoCardItemContent(it) }
        }
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

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalTvMaterial3Api
@Composable
fun PhotoCardItemContent(photoCardContentData: PhotoCardContentData) {
    var titleModifier by remember {
        mutableStateOf(Modifier.padding(4.dp))
    }
    val rectangleShape = androidx.compose.ui.graphics.RectangleShape
    val containerBorder = Border(border = BorderStroke(0.5.dp, Color.White), shape = rectangleShape)
    CompactCard(
        modifier = Modifier.onFocusChanged {
            titleModifier =
                if (it.isFocused) titleModifier.basicMarquee() else Modifier.padding(4.dp)
        },
        border = CardDefaults.border(
            border = containerBorder,
            focusedBorder = containerBorder,
            pressedBorder = containerBorder
        ),
        shape = CardDefaults.shape(
            shape = rectangleShape,
            focusedShape = rectangleShape,
            pressedShape = rectangleShape,
        ),
        glow = CardDefaults.glow(focusedGlow = Glow(Color.White, 8.dp)),
        onClick = {},
        image = {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = photoCardContentData.imageUrl,
                contentDescription = "image",
            ) {
                when (painter.state) {
                    AsyncImagePainter.State.Empty -> painterResource(id = R.mipmap.error_image_generic)
                    is AsyncImagePainter.State.Error -> painterResource(id = R.mipmap.error_image_generic)
                    is AsyncImagePainter.State.Loading -> LoadingImageContainer()
                    is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent(
                        modifier = Modifier.aspectRatio(
                            ASPECT_RATIO_16_9
                        ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        },
        title = {
            Text(
                photoCardContentData.title,
                modifier = titleModifier,
                maxLines = 1,
            )
        },
        subtitle = {
            Text(
                text = photoCardContentData.subtitle,
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                maxLines = 1
            )
        },

        )

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
            photos.collectAsLazyPagingItems(), {})
    }
}