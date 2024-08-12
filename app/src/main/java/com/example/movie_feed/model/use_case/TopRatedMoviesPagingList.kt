package com.example.movie_feed.model.use_case

import androidx.paging.PagingData
import com.example.movie_feed.data.MovieItem
import com.example.movie_feed.data.MoviePhotoRepository
import javax.inject.Inject

class TopRatedMoviesPagingList @Inject constructor(private val Repository: MoviePhotoRepository) {
    suspend operator fun invoke(lang: String) :kotlinx.coroutines.flow.Flow<PagingData<MovieItem>> {
//        return Repository.
    }
}