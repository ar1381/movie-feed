package com.example.movie_feed.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movie_feed.ui.screens.search.tools.SearchBar
import com.example.movie_feed.ui.screens.search.tools.SearchEmpty
import com.example.movie_feed.ui.screens.search.tools.SearchMovieItemCard
import com.example.movie_feed.ui.screens.view_all.tools.ErrorView
import com.example.movie_feed.ui.screens.view_all.tools.IsLoading

@Composable
fun SearchPageScreen(
    navController: NavController,
    viewModel: SearchPageViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SearchBar(
                    onSearch = { state ->
                        state.value.text.let { query ->
                            if (query.isNotBlank()) {
                                viewModel.searchMovie(query)
                            }
                        }
                        viewModel.searchMovie(state.value.text)
                    },
                    onCancel = { viewModel.clearSearch() })
            }
        }

        Box(modifier = Modifier.padding(top = 10.dp)) {
            SearchItemList(viewModel, navController)
            IsLoading(isLoading = viewModel.isLoading.value)
            ErrorView(viewModel.apiError.value)
            SearchEmpty(viewModel.listEmpty.value)
        }
    }
}

@Composable
fun SearchItemList(viewModel: SearchPageViewModel, navController: NavController) {
    LazyColumn() {
        items(
            items = viewModel.searchMoviePagingItems,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            SearchMovieItemCard(item, navController)
        }
    }
}