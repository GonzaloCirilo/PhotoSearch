package com.example.data.remote.dto

import com.example.data.remote.FlickerApiConstants
import org.junit.Assert
import org.junit.Test

class PhotoMapperTest {

    @Test
    fun `given a photo page dto when map then expect correct mapping`() {
        // given
        val dto = PhotosDto(
            page = 1,
            pages = 10,
            perPage = 50,
            total = 500,
            photo = listOf(
                PhotoDto(
                    id = "id",
                    owner = "owner",
                    secret = "secret",
                    server = "server",
                    farm = 4,
                    title = "Photo title",
                    isPublic = 1,
                    isFamily = 1,
                    isFriend = 1,
                    dateUpload = 1691715382,
                    ownerName = "Author"
                )
            )
        )
        // when
        val result = PhotoMapper.map(dto)
        // then
        Assert.assertEquals(1, result.pageNumber)
        Assert.assertEquals(2, result.nextPage)
        with(result.photos[0]){
            Assert.assertEquals("${FlickerApiConstants.IMAGE_BASE_URL}/server/id_secret_z.jpg", photoUrl)
            Assert.assertEquals("Photo title", title)
            Assert.assertEquals("Author / Aug 10 2023", subtitle)
        }
    }

    @Test
    fun `given a photo page dto that is the last one when map then expect correct mapping`() {
        // given
        val dto = PhotosDto(
            page = 10,
            pages = 10,
            perPage = 50,
            total = 500,
            photo = listOf(
                PhotoDto(
                    id = "id",
                    owner = "owner",
                    secret = "secret",
                    server = "server",
                    farm = 4,
                    title = "Photo title",
                    isPublic = 1,
                    isFamily = 1,
                    isFriend = 1,
                    dateUpload = 1691715382,
                    ownerName = "Author"
                )
            )
        )
        // when
        val result = PhotoMapper.map(dto)
        // then
        Assert.assertEquals(10, result.pageNumber)
        Assert.assertEquals(null, result.nextPage)
        with(result.photos[0]){
            Assert.assertEquals("${FlickerApiConstants.IMAGE_BASE_URL}/server/id_secret_z.jpg", photoUrl)
            Assert.assertEquals("Photo title", title)
            Assert.assertEquals("Author / Aug 10 2023", subtitle)
        }
    }

}