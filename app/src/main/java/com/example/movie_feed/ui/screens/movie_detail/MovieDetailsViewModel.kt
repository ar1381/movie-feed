package com.example.movie_feed.ui.screens.movie_detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.movie_feed.model.UseCases
import com.example.movie_feed.model.use_case.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_feed.data.database.MovieEntity
import com.example.movie_feed.data.database.RepositoryData
import com.example.movie_feed.model.Constants
import com.example.movie_feed.model.use_case.GetVideosResponse
import com.example.movie_feed.model.use_case.MovieCreditsResponse
import com.example.movie_feed.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    useCases: UseCases, savedStateHandle: SavedStateHandle, private val repositoryData: RepositoryData
) :
    ViewModel() {


    private val _movieDetailsResponse: MutableState<MovieDetailsResponse> =
        mutableStateOf(MovieDetailsResponse())
    val movieDetailsResponse: State<MovieDetailsResponse> = _movieDetailsResponse

    private val _movieCreditsResponse: MutableState<MovieCreditsResponse> =
        mutableStateOf(MovieCreditsResponse())
    val movieCreditsResponse: State<MovieCreditsResponse> = _movieCreditsResponse

    private val _getVideosResponse: MutableState<GetVideosResponse> =
        mutableStateOf(GetVideosResponse())
    val getVideosResponse: State<GetVideosResponse> = _getVideosResponse

    private val _apiError = mutableStateOf(false)
    val apiError: State<Boolean> = _apiError

    private var _isLoading = mutableStateMapOf<Int, Boolean>()
    val isLoading: Map<Int,Boolean> = _isLoading

    init {
        initMapValues()
      savedStateHandle.get<String>("movieId")?.let { movieId ->
            if (movieId.isNotEmpty()) {
                viewModelScope.launch {


                    useCases.movieDetails.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _movieDetailsResponse.value = response
                                    delay(1000)
                                    _isLoading[0] = false
                                }

                            }
                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[0] = false
                            }

                            is NetworkResult.Loading -> {

                            }
                        }

                    }
                    useCases.movieCredits.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _movieCreditsResponse.value = response
                                    delay(1000)
                                    _isLoading[1] = false
                                }

                            }
                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[1] = false
                            }

                            is NetworkResult.Loading -> {

                            }
                        }

                    }
                    useCases.getVideos.invoke(Constants.LANG, movieId).collect {
                        when (it) {

                            is NetworkResult.Success -> {
                                it.value.body()?.let { response ->
                                    _getVideosResponse.value = response
                                    delay(1000)
                                    _isLoading[2] = false
                                }

                            }
                            is NetworkResult.Failure -> {
                                _apiError.value = true
                                _isLoading[2] = false
                            }

                            is NetworkResult.Loading -> {

                            }
                        }

                    }


                }

            }
        }
    }
    fun bookmarkMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repositoryData.insertMovie(movieEntity)
        }
    }

    fun unbookmarkMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repositoryData.updateMovie(movieEntity)
        }
    }

    fun getBookmarkedMovies(): LiveData<List<MovieEntity>> {
        return repositoryData.bookmarkedMovies
    }

    private fun initMapValues() {
        _isLoading[0] = true
        _isLoading[1] = true
        _isLoading[2] = true
    }

}