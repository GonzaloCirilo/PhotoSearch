package com.example.photosearch

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.example.photosearch.main_screen.MainScreen
import com.example.photosearch.main_screen.MainScreenViewModel
import com.example.photosearch.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalTvMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val mainScreenViewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        setContent {
            MaterialTheme {
                val state by mainScreenViewModel.state.collectAsState()
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize(),
                ) {
                    MainScreen(onSearchPressed = {
                        mainScreenViewModel.showSearchScreen()
                    })
                }
                if(state.showingSearch) {
                    SearchScreen()
                }
            }
        }
    }

    private fun handleBackPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mainScreenViewModel.state.value.showingSearch){
                    mainScreenViewModel.closeSearchScreen()
                } else {
                    finish()
                }
            }

        })
    }
}