package com.example.movie_feed.model

import com.example.movie_feed.model.use_case.GetVideos
import com.example.movie_feed.model.use_case.MovieCredits
import com.example.movie_feed.model.use_case.MovieDetails
import com.example.movie_feed.model.use_case.NowPlayingMoviesList
import com.example.movie_feed.model.use_case.NowPlayingMoviesPagingList
import com.example.movie_feed.model.use_case.PopularMoviesList
import com.example.movie_feed.model.use_case.PopularMoviesPagingList
import com.example.movie_feed.model.use_case.SearchMoviesPagingList
import com.example.movie_feed.model.use_case.TopRatedMoviesList
import com.example.movie_feed.model.use_case.TopRatedMoviesPagingList
import com.example.movie_feed.model.use_case.UpcomingMoviesList
import com.example.movie_feed.model.use_case.UpcomingMoviesPagingList

class UseCases (
    val topRatedMoviesPagingList: TopRatedMoviesPagingList,
    val popularMoviesPagingList: PopularMoviesPagingList,
    val nowPlayingMoviesPagingList: NowPlayingMoviesPagingList,
    val upcomingMoviesPagingList: UpcomingMoviesPagingList,

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