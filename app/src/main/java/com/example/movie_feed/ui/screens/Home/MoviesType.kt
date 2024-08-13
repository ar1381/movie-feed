package com.example.movie_feed.ui.screens.Home

sealed class MoviesType(val value: String) {
    object POPULAR : MoviesType("Popular")
    object NOW_PLAYING : MoviesType("Now Playing")
    object UPCOMING : MoviesType("Upcoming")
    object TOP_RATED : MoviesType("Top Rated")
}