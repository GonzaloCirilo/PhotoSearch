package com.example.photosearch.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.photosearch.databinding.FragmentSearchPhotoBinding

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Gray)){
        AndroidViewBinding(
            modifier = Modifier.fillMaxSize(),
            factory = FragmentSearchPhotoBinding::inflate
        )
    }
}