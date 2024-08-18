package com.example.movie_feed.model

import com.example.movie_feed.data.MovieApiService
import com.example.movie_feed.data.MovieFeedRepository
import com.example.movie_feed.model.Constants.CONNECT_TIMEOUT
import com.example.movie_feed.model.Constants.READ_TIMEOUT
import com.example.movie_feed.model.Constants.WRITE_TIMEOUT
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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun clientInterceptor() : Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", "dd6acb10c15a4e93ec1e0ccb9f6bcaed")
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = if ("true".toBoolean()) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieApiService = retrofit.create(MovieApiService::class.java)


    @Provides
    @Singleton
    fun useCases(movieRepository: MovieFeedRepository): UseCases = UseCases(
        //paging
        PopularMoviesPagingList(movieRepository),
        NowPlayingMoviesPagingList(movieRepository),
        UpcomingMoviesPagingList(movieRepository),
        TopRatedMoviesPagingList(movieRepository),
        //non-paging
        PopularMoviesList(movieRepository),
        NowPlayingMoviesList(movieRepository),
        UpcomingMoviesList(movieRepository),
        TopRatedMoviesList(movieRepository),

        MovieDetails(movieRepository),
        MovieCredits(movieRepository),
        GetVideos(movieRepository),
        SearchMoviesPagingList(movieRepository),
    )
}