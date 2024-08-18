package com.example.movie_feed.ui.screens.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movie_feed.ui.screens.Home.tools.TopBar
import com.example.movie_feed.ui.screens.components.MovieCard
import com.example.movie_feed.ui.screens.components.Screen
import com.example.movie_feed.R
import com.example.movie_feed.ui.screens.view_all.tools.ErrorView
import com.example.movie_feed.ui.screens.view_all.tools.IsLoading


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = navController)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            LazyColumn() {
                item {
                    TopBar(navController, viewModel.popularMovieList.isNotEmpty())
                    Spacer(modifier = Modifier.height(20.dp))

                    Title(
                        navController,
                        viewModel.popularMovieList.isNotEmpty(),
                        MoviesType.POPULAR
                    )

                    PopularList(viewModel = viewModel, navController = navController)

                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController,
                        viewModel.nowPlayingMovieList.isNotEmpty(),
                        MoviesType.NOW_PLAYING
                    )
                    NowPlayingList(viewModel = viewModel, navController = navController)

                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController,
                        viewModel.upcomingMovieList.isNotEmpty(),
                        MoviesType.UPCOMING
                    )
                    UpcomingList(viewModel = viewModel, navController = navController)

                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController,
                        viewModel.topRatedMovieList.isNotEmpty(),
                        MoviesType.TOP_RATED
                    )
                    TopRatedList(viewModel = viewModel, navController = navController)
                }
            }
            IsLoading(isLoading = viewModel.isLoading.containsValue(true))
            ErrorView(viewModel.apiError.value)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {

//TODO
}

@Composable
fun Title(
    navController: NavController,
    visibilty: Boolean?,
    moviesType: MoviesType
) {
    if (visibilty == true) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = moviesType.value,
                style = MaterialTheme.typography.h6,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(Screen.ViewAll.route + "?moviesType=${moviesType.value}")
                    },
                )
            ) {
                Text(
                    text = "View all",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 10.dp),
                )
                Icon(
                    painter = painterResource(id = R.drawable.double_arrows),
                    contentDescription = "arrow_forward",
                    Modifier.size(10.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PopularList(viewModel: HomeViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.popularMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieCard(item, Modifier.width(140.dp), navController)
        }
    }
}

@Composable
fun NowPlayingList(viewModel: HomeViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.nowPlayingMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieCard(item, Modifier.width(140.dp), navController)
        }
    }
}

@Composable
fun UpcomingList(viewModel: HomeViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.upcomingMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieCard(item, Modifier.width(140.dp), navController)
        }
    }
}

@Composable
fun TopRatedList(viewModel: HomeViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.topRatedMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieCard(item, Modifier.width(140.dp), navController)
        }
    }
}