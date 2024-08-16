package com.example.movie_feed.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movie_feed.MovieFeedApplication
import com.example.movie_feed.data.MoviePhotoRepository
import com.example.movie_feed.model.MoviePhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


//Ui state for main screen

sealed interface MovieUiState{
    data class Success(val photos: List<MoviePhoto>) :MovieUiState
    object Error: MovieUiState
    object Loading: MovieUiState
}

class MovieViewModel(private val moviePhotoRepository: MoviePhotoRepository) : ViewModel() {
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {
        getMoviePhotos()
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
//                val movieDetails: MoviePhoto = moviePhotoRepository.getMoviePhoto(movieId)
//                 Handle the movie details
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun getMoviePhotos() {
        viewModelScope.launch {
            movieUiState = MovieUiState.Loading
            movieUiState = try {
                MovieUiState.Success(moviePhotoRepository.getMoviePhotos())
            } catch (e: IOException) {
                MovieUiState.Error
            } catch (e: HttpException) {
                MovieUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MovieFeedApplication)
                val moviePhotoRepository = application.container.moviePhotoRepository
                MovieViewModel(moviePhotoRepository = moviePhotoRepository)
            }
        }
    }
}