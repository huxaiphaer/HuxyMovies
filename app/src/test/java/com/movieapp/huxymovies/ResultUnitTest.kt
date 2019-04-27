package com.movieapp.huxymovies

import android.arch.lifecycle.LiveData
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.movieapp.huxymovies.model.DetailsModal

import com.movieapp.huxymovies.model.Result

import org.junit.Test
import org.mockito.Mockito

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ResultUnitTest {

    inline fun <reified T: Any> mock() = Mockito.mock(T::class.java)
    /**
     * Test the livedata what it returns.
     */
    @Test
    fun TestLiveData() {

        val pagedListLiveData: LiveData<Result> = mock()
        `when`(pagedListLiveData.hasObservers()).thenReturn(true)

        val pageKeyedDataSourceLiveData: LiveData<PageKeyedDataSource<Long, Result>> = mock()

        `when`(pageKeyedDataSourceLiveData.hasObservers()).thenReturn(true)

    }
}
