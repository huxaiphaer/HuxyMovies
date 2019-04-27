package com.movieapp.huxymovies.datasource

import android.arch.paging.PageKeyedDataSource
import com.movieapp.huxymovies.datasource.remote.RetrofitClient
import com.movieapp.huxymovies.model.MovieApiResponse
import com.movieapp.huxymovies.model.Result
import com.movieapp.huxymovies.utils.Utils.API_KEY
import com.movieapp.huxymovies.utils.Utils.LANGUAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultDataSource : PageKeyedDataSource<Long, Result>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Long>, callback: PageKeyedDataSource.LoadInitialCallback<Long, Result>) {

        RetrofitClient.instance
                .api
                .getMovies(API_KEY, FIRST_PAGE, LANGUAGE)
                .enqueue(object : Callback<MovieApiResponse> {
                    override fun onResponse(call: Call<MovieApiResponse>, response: Response<MovieApiResponse>) {

                        if (response.body() != null) {

                            callback.onResult(response.body()!!.mResults!!, null, FIRST_PAGE + 1L)

                        }
                    }

                    override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {

                    }
                })

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, Result>) {


        RetrofitClient.instance
                .api
                .getMovies(API_KEY, params.key, LANGUAGE)
                .enqueue(object : Callback<MovieApiResponse> {
                    override fun onResponse(call: Call<MovieApiResponse>, response: Response<MovieApiResponse>) {

                        val key = if (params.key > 1) params.key - 1 else null

                        if (response.body() != null) {

                            callback.onResult(response.body()!!.mResults!!, key)

                        }
                    }

                    override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {

                    }
                })

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, Result>) {


        RetrofitClient.instance
                .api
                .getMovies(API_KEY, params.key, LANGUAGE)
                .enqueue(object : Callback<MovieApiResponse> {
                    override fun onResponse(call: Call<MovieApiResponse>, response: Response<MovieApiResponse>) {

                        val key = if (response.body()!!.mTotalPages!! > response.body()!!.mPage!!) params.key + 1 else null

                        if (response.body() != null) {

                            callback.onResult(response.body()!!.mResults!!, key)
                        }
                    }

                    override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {

                    }
                })
    }

    companion object {


        private val FIRST_PAGE: Long = 1
    }
}