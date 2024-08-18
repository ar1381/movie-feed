package com.example.movie_feed.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie_feed.data.MovieApiService
import com.example.movie_feed.data.MovieItem
import com.example.movie_feed.model.Constants.NETWORK_PAGE_SIZE
import com.example.movie_feed.model.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class TopRatedPagingSource(private val apiService: MovieApiService, private val lang:String) : PagingSource<Int, MovieItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = apiService.getTopRated(
                page = position,
                language = lang
            )
            val nextKey = if (data.results?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = data.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }
}