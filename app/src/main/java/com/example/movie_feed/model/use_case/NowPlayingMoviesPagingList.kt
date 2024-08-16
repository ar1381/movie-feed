package com.example.movie_feed.model.use_case

import androidx.paging.PagingData
import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.data.MovieItem
import javax.inject.Inject

class NowPlayingMoviesPagingList @Inject constructor(private val movieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String) :kotlinx.coroutines.flow.Flow<PagingData<MovieItem>> {
        return movieRepository.nowPlayingPagingList(lang)
    }
}