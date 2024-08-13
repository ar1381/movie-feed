package com.example.movie_feed.model

import com.example.movie_feed.model.use_case.TopRatedMoviesList
import com.example.movie_feed.model.use_case.TopRatedMoviesPagingList

class UseCases (
    val topRatedMoviesPagingList: TopRatedMoviesPagingList,
    val topRatedMoviesList: TopRatedMoviesList,
    val popularMoviesPagingList: PopularMoviesPagingList,
    val nowPlayingMoviesPagingList: NowPlayingMoviesPagingList,
    val upcomingMoviesPagingList: UpcomingMoviesPagingList,
    //TODO make the fies for all use cases

    //no page
    val popularMoviesList: PopularMoviesList,
    val nowPlayingMoviesList: NowPlayingMoviesList,
    val upcomingMoviesList: UpcomingMoviesList,
    val topRatedMoviesList: TopRatedMoviesList,
    val movieDetails: MovieDetails,
    val movieCredits: MovieCredits,
    val getVideos: GetVideos,
    val searchMoviesPagingList: SearchMoviesPagingList,
    )