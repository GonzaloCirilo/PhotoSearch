package com.example.photosearch.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.photosearch.theme.Aquamarine
import com.example.photosearch.theme.AquamarineDark
import com.example.photosearch.theme.Dimens
import com.example.photosearch.theme.Gray
import com.example.photosearch.theme.Gray700
import com.example.photosearch.theme.Gray800
import com.example.photosearch.theme.Typography

@Composable
fun MainScreen() {
    MainScreenContent()
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainScreenContent() {
    TvLazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.mainScreenPadding,
                start = Dimens.mainScreenPadding,
                end = Dimens.mainScreenPadding
            ),
        columns = TvGridCells.Adaptive(minSize = 300.dp)
    ) {
        item(span = { TvGridItemSpan(maxLineSpan) }) {
            MainComposableHeader()
        }

    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainComposableHeader(modifier: Modifier = Modifier) {
    Column {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier.size(64.dp),
                onClick = { /*TODO*/ },
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
            text = "Trending Now On Flickr",
            modifier = Modifier.padding(top = Dimens.subtitleTopPadding),
        )
    }
}

@ExperimentalTvMaterial3Api
@Composable
fun PhotoCardItemContent() {
    CompactCard(
        onClick = {},
        image = {},
        title = { Text("card title") },
        subtitle = { Text(text = "cardSubtitle") },
    )

}


@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(showBackground = true, widthDp = 1920, heightDp = 1080)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreenContent()
    }
}