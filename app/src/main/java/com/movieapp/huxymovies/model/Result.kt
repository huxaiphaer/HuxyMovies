package com.movieapp.huxymovies.model

import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("id")
    var mId: Long? = null
    @SerializedName("overview")
    var mOverview: String? = null
    @SerializedName("poster_path")
    var mPosterPath: String? = null
    @SerializedName("title")
    var mTitle: String? = null
    @SerializedName("vote_average")
    var mVoteAverage: Double? = null

}
