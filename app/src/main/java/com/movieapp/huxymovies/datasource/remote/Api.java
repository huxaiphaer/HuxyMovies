package com.movieapp.huxymovies.datasource.remote;

import com.movieapp.huxymovies.model.DetailsModal;
import com.movieapp.huxymovies.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("movie/popular")
    Call<MovieApiResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("page") long page,
            @Query("language") String language

    );

    @GET("movie/{movie_id}")
    Call<DetailsModal> getSingleMovie(
            @Path("movie_id") Long movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language

    );
}
