package com.example.movie_feed.ui.screens.view_all

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie_feed.model.MovieTypes
import com.example.movie_feed.ui.screens.components.MovieCard
import com.example.movie_feed.ui.screens.view_all.tools.ToolBar

@Composable
fun ViewAllScreen(
    navController: NavController,
    moviesType: String,
//    viewModel: ViewAllViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        ToolBar(title = moviesType, onBack = {
            navController.popBackStack()
        })
    }) { paddingValues ->
        Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            val movieItems = selectList(moviesType, viewModel)
            val modifier =
                if (movieItems.loadState.append == LoadState.Loading)
                    Modifier.padding(bottom = 80.dp)
                else Modifier.fillMaxSize()
            LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(128.dp), content = {
                items(movieItems.itemCount) { i ->
                    Row {
                        movieItems[i]?.let {
                            MovieCard(
                                item = it,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                navController = navController
                            )
                        }
                    }
                }
            })
            if (movieItems.loadState.append == LoadState.Loading)
                PaginationProgress()
            else {
                handlePagingResult(movieItems)
            }
        }
    }
}

@Composable
fun selectList(moviesType: String, viewModel: ViewAllViewModel): LazyPagingItems<MovieItem> {
    return when (moviesType) {
        MovieTypes.POPULAR.value -> viewModel.popularMoviesPagingItems.collectAsLazyPagingItems()
        //TODO
        //other movie types
        else -> {
//            viewModel.topRatedMoviesPagingItems.collectAsLazyPagingItems()
        }
    }
}