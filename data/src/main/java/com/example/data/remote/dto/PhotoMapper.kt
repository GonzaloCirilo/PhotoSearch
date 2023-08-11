package com.example.data.remote.dto

import com.example.data.remote.FlickerApiConstants
import com.example.domain.model.Photo
import com.example.domain.model.PhotoPage
import java.text.SimpleDateFormat
import java.util.Locale

object PhotoMapper {

    fun map(photosDto: PhotosDto): PhotoPage {
        return PhotoPage(
            photos = photosDto.photo.map {
                Photo(
                    title = it.title,
                    subtitle = "${it.ownerName} / ${it.dateUpload.toFormattedDate()}",
                    photoUrl = "${FlickerApiConstants.IMAGE_BASE_URL}/${it.server}/${it.id}_${it.secret}_z.jpg"
                )
            },
            pageNumber = photosDto.page.toInt(),
            nextPage = photosDto.getNextPage()
        )
    }

    private fun PhotosDto.getNextPage(): Int? {
        val nextPage = if (page == pages) null else page + 1
        return nextPage?.toInt()
    }

    private fun Long.toFormattedDate(): String {
        val simpleDateFormat = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
        return simpleDateFormat.format(this * 1000)
    }
}