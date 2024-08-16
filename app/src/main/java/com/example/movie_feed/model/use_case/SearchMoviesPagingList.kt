package com.example.movie_feed.model.use_case

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SearchMoviesPagingList @Inject constructor(private val movieRepository: MovieFeedRepository) {
    suspend operator fun invoke(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>> {
        return movieRepository.searchPagingList(query = query, lang = lang)
    }
}