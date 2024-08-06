package com.example.movie_feed.network

import com.example.movie_feed.model.MoviePhoto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiServise{

    @GET("photo")
    suspend fun getPhotos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):List<MoviePhoto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): List<MoviePhoto>
}