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
    private val baseUrl = ""
    private val apiKey = "dd6acb10c15a4e93ec1e0ccb9f6bcaed"

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
    fun getApiKey(): String {
        return apiKey
    }
}