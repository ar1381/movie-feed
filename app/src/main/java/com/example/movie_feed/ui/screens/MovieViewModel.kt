package com.example.movie_feed.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movie_feed.model.MoviePhoto

/**
 * Ui state for main screen
 */

sealed interface MovieUiState{
    data class Success(val photos: List<MoviePhoto>)
    object Error: MovieUiState
    object Loading: MovieUiState
}

class MovieViewModel(): ViewModel{
    var movieUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    init {

    }

}