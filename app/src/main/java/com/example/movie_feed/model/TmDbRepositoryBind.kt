package com.example.movie_feed.model

import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.data.MovieFeedRepositoryimpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MovieFeedRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(moviefeedRepositoryImpl: MovieFeedRepositoryimpl): MovieFeedRepository

}