package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.data.TopRatedMovieResponse
import com.example.movie_feed.network.NetworkResult
import javax.inject.Inject

class TopRatedMoviesList @Inject constructor(private val MovieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<TopRatedMovieResponse>> {
        return MovieRepository.topRatedList(lang, page)
    }
}