package com.movieapp.huxymovies;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.movieapp.huxymovies.model.Result;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultUnitTest {

    /**
     * Test the livedata what it returns.
     */
    @Test
    public void TestLiveData() {

        LiveData<PagedList<Result>> pagedListLiveData = mock(LiveData.class);
        when(pagedListLiveData.hasObservers()).thenReturn(true);

        LiveData<PageKeyedDataSource<Long, Result>> pageKeyedDataSourceLiveData = mock(LiveData.class);
        when(pageKeyedDataSourceLiveData.hasObservers()).thenReturn(true);

    }
}
