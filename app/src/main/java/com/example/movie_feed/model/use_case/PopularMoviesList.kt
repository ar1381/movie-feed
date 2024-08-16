package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.model.responce.PopularMovieResponse
import com.example.movie_feed.network.NetworkResult
import javax.inject.Inject

class PopularMoviesList @Inject constructor(private val movieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<PopularMovieResponse>> {
        return movieRepository.popularList(lang, page)
    }
}