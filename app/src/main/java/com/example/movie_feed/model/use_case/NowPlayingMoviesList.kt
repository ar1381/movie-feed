package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.model.responce.NowPlayingMovieResponse
import com.example.movie_feed.network.NetworkResult
import javax.inject.Inject

class NowPlayingMoviesList @Inject constructor(private val movieRepository: MovieFeedRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<NetworkResult<NowPlayingMovieResponse>> {
        return movieRepository.nowPlayingList(lang, page)
    }
}