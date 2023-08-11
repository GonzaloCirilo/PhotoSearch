package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    @SerializedName("ispublic")
    val isPublic: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("isfamily")
    val isFamily: Int,
    @SerializedName("ispublic")
    val dateUpload: Long,
    @SerializedName("ownername")
    val ownerName: String,
)
