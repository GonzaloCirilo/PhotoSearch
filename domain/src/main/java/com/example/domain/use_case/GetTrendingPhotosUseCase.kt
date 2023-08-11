package com.example.domain.use_case

import com.example.domain.model.PhotoPage
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class GetTrendingPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {

    suspend fun execute(pageSize: Int, pageNumber: Int): PhotoPage {
        return photoRepository.getTrendingPhotos(pageNumber, pageSize)
    }
}