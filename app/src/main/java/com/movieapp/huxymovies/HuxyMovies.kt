package com.movieapp.huxymovies

import android.app.Application
import android.arch.persistence.room.Room
import com.movieapp.huxymovies.datasource.local.ResultsDatabase

class HuxyMovies : Application() {

    companion object {
        var database: ResultsDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, ResultsDatabase::class.java, "movies_db")
                .fallbackToDestructiveMigration().build()
    }
}