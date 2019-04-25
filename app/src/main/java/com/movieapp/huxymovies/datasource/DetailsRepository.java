package com.movieapp.huxymovies.datasource;

import android.arch.lifecycle.MutableLiveData;

import com.movieapp.huxymovies.datasource.remote.RetrofitClient;
import com.movieapp.huxymovies.model.DetailsModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movieapp.huxymovies.utils.Utils.API_KEY;
import static com.movieapp.huxymovies.utils.Utils.LANGUAGE;

public class DetailsRepository {


    public MutableLiveData<DetailsModal> getSIngleMovies(long movie_id) {
        final MutableLiveData<DetailsModal> moviesList = new MutableLiveData<>();
        RetrofitClient.getInstance()
                .getApi()
                .getSingleMovie(movie_id, API_KEY, LANGUAGE)
                .enqueue(new Callback<DetailsModal>() {
                    @Override
                    public void onResponse(Call<DetailsModal> call, Response<DetailsModal> response) {

                        moviesList.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<DetailsModal> call, Throwable t) {

                    }
                });

        return moviesList;
    }
}
