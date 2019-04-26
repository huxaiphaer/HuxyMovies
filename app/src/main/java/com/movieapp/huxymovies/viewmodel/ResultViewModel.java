package com.movieapp.huxymovies.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.movieapp.huxymovies.datasource.MoviesDataFactory;
import com.movieapp.huxymovies.model.Result;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class ResultViewModel extends ViewModel {

    public LiveData<PagedList<Result>> resultPagedList;
    public LiveData<PageKeyedDataSource<Long, Result>> liveDatSource;

    public ResultViewModel() {

        MoviesDataFactory itemDataSourceFactory = new MoviesDataFactory();
        liveDatSource = itemDataSourceFactory.getResultLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PAGE_SIZE)
                        .build();

        resultPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
