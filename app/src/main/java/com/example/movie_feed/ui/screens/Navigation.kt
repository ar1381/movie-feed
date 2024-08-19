package com.example.movie_feed.ui.screens


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movie_feed.ui.screens.Home.HomeScreen
import com.example.movie_feed.ui.screens.movie_detail.MovieDetailsScreen
import com.example.movie_feed.ui.screens.search.SearchPageScreen
import com.example.movie_feed.ui.screens.view_all.ViewAllScreen

@ExperimentalAnimationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {

        composable(route = Screen.Dashboard.route) {
            HomeScreen(navController = navController)
        }

        //View All movies screen
        composable(
            route = Screen.ViewAll.route + "?moviesType={moviesType}", arguments = listOf(
                navArgument(name = "moviesType") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val moviesType = it.arguments?.getString("moviesType") ?: ""
            ViewAllScreen(navController = navController, moviesType)
        }

        // Movie Details Screen
        composable(
            route = Screen.MovieDetailsScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "moviesTitle") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val moviesTitle = it.arguments?.getString("moviesTitle") ?: ""
            val movieId = it.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(navController = navController, moviesTitle)
        }
//         Search Page Screen
        composable(
            route = Screen.SearchPageScreen.route
        ) {
            SearchPageScreen(navController = navController)
        }


    }
}