package com.example.movie_feed.data

import androidx.paging.PagingData
import com.example.movie_feed.model.responce.NowPlayingMovieResponse
import com.example.movie_feed.model.responce.PopularMovieResponse
import com.example.movie_feed.model.responce.UpcomingMovieResponse
import com.example.movie_feed.model.use_case.GetVideosResponse
import com.example.movie_feed.model.use_case.MovieCreditsResponse
import com.example.movie_feed.model.use_case.MovieDetailsResponse
import com.example.movie_feed.model.use_case.SearchMovieResponse
import com.example.movie_feed.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieFeedRepository {
    //paging
    suspend fun popularPagingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun nowPlayingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun upcomingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun topRatedPagingList(lang: String): Flow<PagingData<MovieItem>>

    //non-paging
    suspend fun popularList(lang: String, page: Int): Flow<NetworkResult<PopularMovieResponse>>
    suspend fun nowPlayingList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<NowPlayingMovieResponse>>

    suspend fun upcomingList(lang: String, page: Int): Flow<NetworkResult<UpcomingMovieResponse>>
    suspend fun topRatedList(lang: String, page: Int): Flow<NetworkResult<TopRatedMovieResponse>>

    suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailsResponse>>>

    suspend fun movieCredits(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieCreditsResponse>>>

    suspend fun getVideos(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<GetVideosResponse>>>

    suspend fun searchPagingList(query: String, lang: String): Flow<NetworkResult<Response<SearchMovieResponse>>>
}