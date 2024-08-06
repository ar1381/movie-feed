package com.example.movie_feed.data

import com.example.movie_feed.model.MoviePhoto
import com.example.movie_feed.network.MovieApiServise

interface MoviePhotoRepository{
    suspend fun getMoviePhoto(): List<MoviePhoto>
}

class NetworkMovieRepository(
    private val movieApiServise: MovieApiServise
): MoviePhotoRepository{
    override suspend fun getMoviePhoto(): List<MoviePhoto> =movieApiServise.getPhotos()
}