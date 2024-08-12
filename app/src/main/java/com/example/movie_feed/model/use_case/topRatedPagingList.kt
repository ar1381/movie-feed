package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MoviePhotoRepository
import com.example.movie_feed.data.TopRatedMovieResponse
import com.example.movie_feed.network.NetworkResult
import javax.inject.Inject

class TopRatedMoviesList @Inject constructor(private val MovieRepository: MoviePhotoRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<TopRatedMovieResponse>> {
//        return MovieRepository.
    }
}