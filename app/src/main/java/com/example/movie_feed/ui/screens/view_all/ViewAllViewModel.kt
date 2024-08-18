package com.example.movie_feed.ui.screens.view_all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.PagingData
import com.example.movie_feed.data.MovieItem
import com.example.movie_feed.model.Constants
import com.example.movie_feed.model.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ViewAllViewModel @Inject constructor(useCases: UseCases) :
    ViewModel() {

    var popularMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var nowPlayingMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var upcomingMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var topRatedMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()


    init {
        viewModelScope.launch {

            popularMoviesPagingItems = useCases.popularMoviesPagingList.invoke(Constants.LANG)
            nowPlayingMoviesPagingItems = useCases.nowPlayingMoviesPagingList.invoke(Constants.LANG)
            upcomingMoviesPagingItems = useCases.upcomingMoviesPagingList.invoke(Constants.LANG)
            topRatedMoviesPagingItems = useCases.topRatedMoviesPagingList.invoke(Constants.LANG)

        }
    }
}