package com.movieapp.huxymovies.datasource.remote

import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.model.MovieApiResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("page") page: Long,
            @Query("language") language: String

    ): Call<MovieApiResponse>

    @GET("movie/{movie_id}")
    fun getSingleMovie(
            @Path("movie_id") movie_id: Long?,
            @Query("api_key") apiKey: String,
            @Query("language") language: String

    ): Call<DetailsModal>
}
