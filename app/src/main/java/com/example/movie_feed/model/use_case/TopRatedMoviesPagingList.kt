package com.example.movie_feed.model.use_case

import androidx.paging.PagingData
import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.data.MovieItem
import javax.inject.Inject

class TopRatedMoviesPagingList @Inject constructor(private val MovieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String) :kotlinx.coroutines.flow.Flow<PagingData<MovieItem>> {
        return MovieRepository.topRatedPagingList(lang)
    }
}