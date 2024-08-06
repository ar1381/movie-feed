package com.example.movie_feed

import android.app.Application
import com.example.movie_feed.data.AppContainer
import com.example.movie_feed.data.DefaultAppContainer

class MovieFeedApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}