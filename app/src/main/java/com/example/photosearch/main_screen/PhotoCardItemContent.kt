package com.example.photosearch.main_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.Text
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.photosearch.R

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
