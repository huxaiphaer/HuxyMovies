package com.movieapp.huxymovies.datasource.local

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.movieapp.huxymovies.model.Result

@Dao
interface ResultsDao {

    @Query("SELECT * FROM Results")
    fun getAllMovies(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(moviesList: List<Result>)

    @Query("DELETE FROM Results")
    fun deleteAllMovies()

    @Query("SELECT * FROM Results")
    fun selectPaged():  DataSource.Factory<Int, Result>

}

