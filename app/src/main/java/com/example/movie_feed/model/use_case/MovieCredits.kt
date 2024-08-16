package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.network.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class MovieCredits @Inject constructor(private val movieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String, movieId: String) :kotlinx.coroutines.flow.Flow<NetworkResult<Response<MovieCreditsResponse>>> {
        return movieRepository.movieCredits(lang, movieId)
    }
}