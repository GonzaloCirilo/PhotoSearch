package com.example.domain.use_case

import com.example.domain.model.PhotoPage

interface GetTrendingPhotosUseCase {

    suspend fun execute(pageSize: Int, pageNumber: Int): PhotoPage

}