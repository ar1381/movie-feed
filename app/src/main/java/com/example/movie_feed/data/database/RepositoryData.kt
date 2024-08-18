package com.example.movie_feed.data.database// RepositoryData.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryData @Inject constructor(private val movieDao: MovieDao) {

    private val _bookmarkedMovies = MutableLiveData<List<MovieEntity>>()
    val bookmarkedMovies: LiveData<List<MovieEntity>> = _bookmarkedMovies

    suspend fun getBookmarkedMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            movieDao.getBookmarkedMovies()
        }
    }

    suspend fun insertMovie(movieEntity: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovie(movieEntity)
        }
    }

    suspend fun updateMovie(movieEntity: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.updateMovie(movieEntity)
        }
    }

    suspend fun getMovie(id: Int): MovieEntity? {
        return withContext(Dispatchers.IO) {
            movieDao.getMovie(id)
        }
    }
}