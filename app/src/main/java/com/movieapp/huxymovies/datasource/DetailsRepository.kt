package com.movieapp.huxymovies.datasource

import android.arch.lifecycle.MutableLiveData
import com.movieapp.huxymovies.datasource.remote.RetrofitClient
import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.utils.Utils
import com.movieapp.huxymovies.utils.Utils.LANGUAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepository {


    fun getSIngleMovies(movie_id: Long): MutableLiveData<DetailsModal> {
        val moviesList = MutableLiveData<DetailsModal>()
        RetrofitClient.instance
                .api
                .getSingleMovie(movie_id, Utils.API_KEY, LANGUAGE)
                .enqueue(object : Callback<DetailsModal> {
                    override fun onResponse(call: Call<DetailsModal>, response: Response<DetailsModal>) {

                        moviesList.setValue(response.body())
                    }

                    override fun onFailure(call: Call<DetailsModal>, t: Throwable) {

                    }
                })

        return moviesList
    }
}
