package com.example.domain.model

data class PhotoPage(
    val photos: List<Photo>,
    val pageNumber: Int,
    val nextPage: Int?,
)
