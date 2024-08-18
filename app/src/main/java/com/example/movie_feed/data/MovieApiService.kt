package com.example.movie_feed.data

import com.example.movie_feed.model.responce.NowPlayingMovieResponse
import com.example.movie_feed.model.responce.PopularMovieResponse
import com.example.movie_feed.model.responce.UpcomingMovieResponse
import com.example.movie_feed.model.use_case.GetVideosResponse
import com.example.movie_feed.model.use_case.MovieCreditsResponse
import com.example.movie_feed.model.use_case.MovieDetailsResponse
import com.example.movie_feed.model.use_case.SearchMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): NowPlayingMovieResponse


    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): PopularMovieResponse



    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): UpcomingMovieResponse


    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ):TopRatedMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<MovieDetailsResponse>


    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<MovieCreditsResponse>


    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<GetVideosResponse>


    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): Response<SearchMovieResponse>
}