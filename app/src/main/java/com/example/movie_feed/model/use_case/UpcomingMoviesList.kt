package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.model.responce.UpcomingMovieResponse
import com.example.movie_feed.network.NetworkResult
import javax.inject.Inject

class UpcomingMoviesList @Inject constructor(private val MovieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<UpcomingMovieResponse>> {
        return MovieRepository.upcomingList(lang, page)
    }
}