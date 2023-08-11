package com.example.domain.use_case

import com.example.domain.model.PhotoPage
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class SearchPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {

    suspend fun execute(query: String, pageSize: Int, pageNumber: Int): PhotoPage {
        return photoRepository.serachPhotos(pageNumber, pageSize, query)
    }
}