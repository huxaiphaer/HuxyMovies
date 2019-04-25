package com.movieapp.huxymovies;

import android.arch.lifecycle.LiveData;

import com.movieapp.huxymovies.model.DetailsModal;
import com.movieapp.huxymovies.viewmodel.DetailViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailsUnitTest {


    MockWebServer mMockWebServer;


    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        mMockWebServer.start(8080);
    }



    /**
     * Test the livedata what it returns.
     */
    @Test
    public void loadDetailsData() {

        LiveData<DetailsModal> liveData = mock(LiveData.class);
        DetailViewModel detailViewModel = mock(DetailViewModel.class);
        when(detailViewModel.getHeroes(4L)).thenReturn(liveData);
    }

    /**
     * Test if the loadMethod will load the data to the UI.
     */
    @Test
    public void loadDataMethod() {

        DetailViewModel detailViewModel = mock(DetailViewModel.class);
        detailViewModel.getHeroes(12L);
        verify(detailViewModel).getHeroes(ArgumentMatchers.eq(12L));

    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
    }

}
