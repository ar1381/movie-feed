package com.example.movie_feed.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.movie_feed.ui.screens.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MovieFeedApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = { MovieFeedTopAppBar(scrollBehavior = scrollBehavior) }
    ){
        Surface (
            modifier = Modifier.fillMaxSize()
        ){
            val movieFeedViewModel :MovieViewModel =
                viewModel()
            HomeScreen(
                movieUiState = movieFeedViewModel.marsUiState,
                retryAction = movieFeedViewModel::getMarsPhotos,
                contentPadding = it
            )
        }

    }
}
@Composable
fun MovieFeedTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}