package com.example.photosearch.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.photosearch.theme.Gray700

@Composable
fun LoadingImageContainer() {
    Box(
        modifier = Modifier
            .background(Gray700)
            .fillMaxSize()
            .aspectRatio(1.777f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}