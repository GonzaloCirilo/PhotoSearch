package com.example.domain.use_case

import com.example.domain.model.PhotoPage

interface SearchPhotosUseCase {

    suspend fun execute(query: String, pageSize: Int, pageNumber: Int): PhotoPage
}