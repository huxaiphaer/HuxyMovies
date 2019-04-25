package com.movieapp.huxymovies.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.movieapp.huxymovies.datasource.DetailsRepository;
import com.movieapp.huxymovies.model.DetailsModal;

public class DetailViewModel extends ViewModel {


    //this is the data that we will fetch asynchronously
    public MutableLiveData<DetailsModal> moviesList;

    public DetailViewModel() {

    }

    //we will call this method to get the data
    public LiveData<DetailsModal> getHeroes(long movie_id) {
        //if the list is null
        if (moviesList == null) {
            moviesList = new MutableLiveData<DetailsModal>();
            //we will load it asynchronously from server in this method
            loadHeroes(movie_id);
        }

        //finally we will return the list
        return moviesList;
    }

    private void loadHeroes(long movie_id) {

        DetailsRepository detailsRepository = new DetailsRepository();
        moviesList = detailsRepository.getSIngleMovies(movie_id);

    }
}
