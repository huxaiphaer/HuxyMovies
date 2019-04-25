package com.movieapp.huxymovies.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.movieapp.huxymovies.model.Result;

public class MoviesDataFactory extends android.arch.paging.DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Long, Result>> resultLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {

        ResultDataSource resultDataSource = new ResultDataSource();

        resultLiveDataSource.postValue(resultDataSource);

        return resultDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Long, Result>> getResultLiveDataSource() {
        return resultLiveDataSource;
    }
}