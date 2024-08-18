package com.example.movie_feed.model

sealed class MovieTypes(val value: String) {
    object POPULAR : MovieTypes("Popular")
    object NOW_PLAYING : MovieTypes("Now Playing")
    object TOP_RATED : MovieTypes("Top Rated")
    object UPCOMING : MovieTypes("Upcoming")
}