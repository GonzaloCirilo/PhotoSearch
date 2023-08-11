package com.example.photosearch.paging

import com.example.domain.model.Photo
import com.example.domain.use_case.GetTrendingPhotosUseCase
import java.io.IOException
import javax.inject.Inject

class TrendingPhotosPagingSource @Inject constructor(
    private val getTrendingPhotosUseCase: GetTrendingPhotosUseCase,
) : PhotoPagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val nextPageNumber = params.key ?: 1 // defaults to page 1
            val response =
                getTrendingPhotosUseCase.execute(PagingConfiguration.PAGE_SIZE, nextPageNumber)
            LoadResult.Page(
                data = response.photos,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPage
            )
        } catch (e: IOException) {
            // IOException for network failures.
            LoadResult.Error(e)
        } catch (e: IOException) {
            // Catch other errors
            LoadResult.Error(e)
        }
    }
}