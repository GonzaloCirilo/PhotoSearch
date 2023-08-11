package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotosDto(
    val page: Long,
    val pages: Long,
    @SerializedName("perpage")
    val perPage: Long,
    val total: Long,
    val photo: List<PhotoDto>
)
