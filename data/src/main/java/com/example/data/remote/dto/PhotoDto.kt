package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    @SerializedName("dateupload")
    val dateUpload: Long,
    @SerializedName("ownername")
    val ownerName: String,
)
