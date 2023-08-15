package com.example.photosearch.fake

import com.example.domain.model.Photo
import com.example.domain.model.PhotoPage

object PhotoPageFaker {

    fun createFakePhotoPage(
        photos: List<Photo> = emptyList(),
        pageNumber: Int = 1,
        nextPage: Int? = 2,
    ) = PhotoPage(
        photos = photos,
        pageNumber = pageNumber,
        nextPage = nextPage,
    )

    fun createFakePhoto(
        title: String = "title",
        subtitle: String = "subtitle",
        photoUrl: String = "https://this.is.a.photo.jpg",
    ) = Photo(
        title,
        subtitle, photoUrl
    )
}