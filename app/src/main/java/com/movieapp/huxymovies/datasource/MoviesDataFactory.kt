package com.movieapp.huxymovies.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource

import com.movieapp.huxymovies.model.Result

class MoviesDataFactory : android.arch.paging.DataSource.Factory<Long, Result>() {


    val resultLiveDataSource = MutableLiveData<PageKeyedDataSource<Long, Result>>()

    override fun create(): DataSource<Long, Result> {

        val resultDataSource = ResultDataSource()

        resultLiveDataSource.postValue(resultDataSource)

        return resultDataSource
    }
}