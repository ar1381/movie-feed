package com.example.movie_feed.data

import com.example.movie_feed.network.MovieApiServise
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val moviePhotoRepository: MoviePhotoRepository
}
class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://api.themoviedb.org/3/movie/movie_id?language=en-US"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MovieApiServise by lazy {
        retrofit.create(MovieApiServise::class.java)
    }

    override val moviePhotoRepository: MoviePhotoRepository by lazy {
        NetworkMovieRepository(retrofitService)
    }
}