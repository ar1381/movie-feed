package com.example.movie_feed.ui.screens.components

sealed class Screen(val route:String){
    object Dashboard: Screen("home_screen")
    object ViewAll: Screen("view_all_screen")
    object MovieDetailsScreen: Screen("movie_details_screen")
    object SearchPageScreen: Screen("search_page_screen")
}