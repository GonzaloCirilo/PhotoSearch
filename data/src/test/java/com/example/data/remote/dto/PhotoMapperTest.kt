package com.example.data.remote.dto

import com.example.data.remote.FlickerApiConstants
import com.example.data.remote.dto.mapper.PhotoMapper
import org.junit.Assert
import org.junit.Test

class PhotoMapperTest {

    @Test
    fun `given a photo page dto when map then expect correct mapping`() {
        // given
        val dto = getFakePhotosDto()
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
        val dto = getFakePhotosDto(
            page = 10,
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

    @Test
    fun `given a photo page dto with a photo with owner name when map then expect correct mapping`() {
        // given
        val dto = getFakePhotosDto(
            photo = listOf(
                getFakePhotoDto(ownerName = "")
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
            Assert.assertEquals("No Author / Aug 10 2023", subtitle)
        }
    }

    @Test
    fun `given a photo page dto with a photo with empty title when map then expect correct mapping`() {
        // given
        val dto = getFakePhotosDto(
            photo = listOf(
                getFakePhotoDto(title = "")
            )
        )
        // when
        val result = PhotoMapper.map(dto)
        // then
        Assert.assertEquals(1, result.pageNumber)
        Assert.assertEquals(2, result.nextPage)
        with(result.photos[0]){
            Assert.assertEquals("${FlickerApiConstants.IMAGE_BASE_URL}/server/id_secret_z.jpg", photoUrl)
            Assert.assertEquals("No Title", title)
            Assert.assertEquals("Author / Aug 10 2023", subtitle)
        }
    }

    private fun getFakePhotoDto(
        id: String = "id",
        owner: String = "owner",
        secret: String = "secret",
        server: String = "server",
        farm: Long = 4,
        title: String = "Photo title",
        dateUpload: Long = 1691715382,
        ownerName: String = "Author"
    ) = PhotoDto(
        id,
        owner,
        secret,
        server,
        farm,
        title,
        dateUpload,
        ownerName
    )

    private fun getFakePhotosDto(
        page: Long = 1,
        pages: Long = 10,
        photo: List<PhotoDto> = listOf(
            getFakePhotoDto()
        )
    ) = PhotosDto(
        page = page,
        pages = pages,
        perPage = 50,
        total = 500,
        photo = photo
    )


}