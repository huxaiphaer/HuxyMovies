package com.movieapp.huxymovies.model

import com.google.gson.annotations.SerializedName

class DetailsModal(@field:SerializedName("title")
                   var mTitle: String) {


    @SerializedName("genres")
    var mGenres: List<Genre>? = null
    @SerializedName("poster_path")
    var mPosterPath: String? = null
    @SerializedName("release_date")
    var mReleaseDate: String? = null
    @SerializedName("vote_average")
    var mVoteAverage: Double? = null

}
