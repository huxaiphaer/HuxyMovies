package com.movieapp.huxymovies.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    val api: Api
        get() = retrofit.create(Api::class.java)

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    companion object {


        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private var mInstance: RetrofitClient? = null

        val instance: RetrofitClient
            @Synchronized get() {

                if (mInstance == null) {

                    mInstance = RetrofitClient()
                }

                return mInstance as RetrofitClient

            }
    }
}
