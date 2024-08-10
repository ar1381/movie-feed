package com.example.movie_feed.data

import com.example.movie_feed.model.MoviePhoto
import com.example.movie_feed.network.MovieApiServise

interface MoviePhotoRepository{
    suspend fun getMoviePhoto(movieId :Int): List<MoviePhoto>
}

class NetworkMovieRepository(
    private val movieApiServise: MovieApiServise
): MoviePhotoRepository{
    private val apiKey = ""
    override suspend fun getMoviePhoto(movieId : Int): List<MoviePhoto> = movieApiServise.getPhotos(movieId)
}