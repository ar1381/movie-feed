package com.example.movie_feed.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object {
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                    .build()
            }
            return instance!!
        }
    }
}