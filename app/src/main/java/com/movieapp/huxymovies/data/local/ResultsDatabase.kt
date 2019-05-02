package com.movieapp.huxymovies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.movieapp.huxymovies.model.Result

@Database(entities = [(Result::class)], version = 1)
abstract class ResultsDatabase : RoomDatabase() {

    abstract fun resultsDao(): ResultsDao
}