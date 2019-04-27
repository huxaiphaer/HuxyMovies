package com.movieapp.huxymovies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList

import com.movieapp.huxymovies.datasource.MoviesDataFactory
import com.movieapp.huxymovies.model.Result

import android.nfc.tech.MifareUltralight.PAGE_SIZE

class ResultViewModel : ViewModel() {

    var resultPagedList: LiveData<PagedList<Result>>
    var liveDatSource: LiveData<PageKeyedDataSource<Long, Result>>

    init {

        val itemDataSourceFactory = MoviesDataFactory()
        liveDatSource = itemDataSourceFactory.resultLiveDataSource

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build()

        resultPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()

    }
}
