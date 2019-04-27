package com.movieapp.huxymovies

import android.arch.lifecycle.LiveData

import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.viewmodel.DetailViewModel

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class MovieDetailsUnitTest {

    inline fun <reified T: Any> mock() = Mockito.mock(T::class.java)

    /**
     * Test the livedata what it returns.
     */
    @Test
    fun loadDetailsData() {


        val liveData: LiveData<DetailsModal> = mock()
        val detailViewModel: DetailViewModel = mock()
        `when`(detailViewModel.getHeroes(4L)).thenReturn(liveData)
    }

    /**
     * Test if the loadMethod will load the data to the UI.
     */
    @Test
    fun loadDataMethod() {

        val detailViewModel: DetailViewModel = mock();
        detailViewModel.getHeroes(12L)
        verify(detailViewModel).getHeroes(ArgumentMatchers.eq(12L))

    }

}
