package com.movieapp.huxymovies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.movieapp.huxymovies.datasource.DetailsRepository
import com.movieapp.huxymovies.model.DetailsModal

class DetailViewModel : ViewModel() {


    //this is the data that we will fetch asynchronously
    var moviesList: MutableLiveData<DetailsModal>? = null

    //we will call this method to get the data
    fun getHeroes(movie_id: Long): LiveData<DetailsModal> {
        //if the list is null
        if (moviesList == null) {
            moviesList = MutableLiveData()
            //we will load it asynchronously from server in this method
            loadHeroes(movie_id)
        }

        //finally we will return the list
        return moviesList as MutableLiveData
    }

    private fun loadHeroes(movie_id: Long) {

        val detailsRepository = DetailsRepository()
        moviesList = detailsRepository.getSIngleMovies(movie_id)

    }
}
