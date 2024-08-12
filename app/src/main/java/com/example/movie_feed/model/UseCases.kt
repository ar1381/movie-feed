package com.example.movie_feed.model

import com.example.movie_feed.model.use_case.TopRatedMoviesList
import com.example.movie_feed.model.use_case.TopRatedMoviesPagingList

class UseCases (
    val topRatedMoviesPagingList: TopRatedMoviesPagingList,
    val topRatedMoviesList: TopRatedMoviesList
    )