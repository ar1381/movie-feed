package com.example.movie_feed.ui.screens.search

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movie_feed.data.MovieItem
import com.example.movie_feed.model.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.movie_feed.model.Constants
import com.example.movie_feed.network.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    private var _searchMoviePagingItems = mutableStateListOf<MovieItem>()
    val searchMoviePagingItems: List<MovieItem> = _searchMoviePagingItems

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    private var _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    private val _listEmpty = mutableStateOf(false)
    val listEmpty: State<Boolean> = _listEmpty

    fun searchMovie(query: String) {
        println("searchMovie: $query")
        viewModelScope.launch {
            useCases.searchMoviesPagingList.invoke(query, Constants.LANG).collect {
                when (it) {

                    is NetworkResult.Success -> {

                        _searchMoviePagingItems.clear()
                        val results = it.value.body()?.results
                        if (results.isNullOrEmpty()) {
                            _listEmpty.value = true
                        } else {
                            _listEmpty.value = false
                            _searchMoviePagingItems.addAll(results)
                        }
                        _isLoading.value = false

                    }
                    is NetworkResult.Failure -> {
                        _searchMoviePagingItems.clear()
                        _apiError.value = true
                        _isLoading.value = false
                        _listEmpty.value = false
                    }

                    is NetworkResult.Loading -> {
                        _isLoading.value = true
                        _listEmpty.value = false
                    }
                }

            }
        }
    }

    fun clearSearch() {
        _searchMoviePagingItems.clear()
        _listEmpty.value = false
    }
}