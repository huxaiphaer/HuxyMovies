package com.movieapp.huxymovies.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.movieapp.huxymovies.model.MovieApiResponse;
import com.movieapp.huxymovies.model.Result;
import com.movieapp.huxymovies.datasource.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movieapp.huxymovies.utils.Utils.API_KEY;
import static com.movieapp.huxymovies.utils.Utils.LANGUAGE;


public class ResultDataSource extends PageKeyedDataSource<Long, Result> {


    private static final long FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getMovies(API_KEY, FIRST_PAGE, LANGUAGE)
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().mResults, null, FIRST_PAGE + 1L);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {


        RetrofitClient.getInstance()
                .getApi()
                .getMovies(API_KEY, params.key, LANGUAGE)
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        Long key = (params.key > 1) ? params.key - 1 : null;

                        if (response.body() != null) {

                            callback.onResult(response.body().mResults, key);

                        }
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {


        RetrofitClient.getInstance()
                .getApi()
                .getMovies(API_KEY, params.key, LANGUAGE)
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        Long key = response.body().mTotalPages > response.body().mPage ? params.key + 1 : null;

                        if (response.body() != null) {

                            callback.onResult(response.body().mResults, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });
    }
}
