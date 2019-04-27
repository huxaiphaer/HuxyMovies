package com.movieapp.huxymovies.model

import com.google.gson.annotations.SerializedName

class MovieApiResponse {

    var id: Int? = null
    @SerializedName("page")
    var mPage: Long? = null
    @SerializedName("results")
    var mResults: List<Result>? = null
    @SerializedName("total_pages")
    var mTotalPages: Long? = null

}
