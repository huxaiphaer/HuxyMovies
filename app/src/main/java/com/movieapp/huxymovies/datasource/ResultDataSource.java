package com.movieapp.huxymovies.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.movieapp.huxymovies.datasource.remote.RetrofitClient;
import com.movieapp.huxymovies.model.MovieApiResponse;
import com.movieapp.huxymovies.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movieapp.huxymovies.utils.Utils.INSTANCE;


public class ResultDataSource extends PageKeyedDataSource<Long, Result> {


    private static final long FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getMovies(INSTANCE.getAPI_KEY(), FIRST_PAGE, INSTANCE.getLANGUAGE())
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().getMResults(), null, FIRST_PAGE + 1L);


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
                .getMovies(INSTANCE.getAPI_KEY(), params.key, INSTANCE.getLANGUAGE())
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        Long key = (params.key > 1) ? params.key - 1 : null;

                        if (response.body() != null) {

                            callback.onResult(response.body().getMResults(), key);

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
                .getMovies(INSTANCE.getAPI_KEY(), params.key, INSTANCE.getLANGUAGE())
                .enqueue(new Callback<MovieApiResponse>() {
                    @Override
                    public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                        Long key = response.body().getMTotalPages() > response.body().getMPage() ? params.key + 1 : null;

                        if (response.body() != null) {

                            callback.onResult(response.body().getMResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieApiResponse> call, Throwable t) {

                    }
                });
    }
}
