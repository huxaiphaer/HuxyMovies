package com.movieapp.huxymovies.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Results")
class Result {
    constructor(mId: Long?, mOverview: String?, mPosterPath: String?, mTitle: String?, mVoteAverage: Double?) {
        this.mId = mId
        this.mOverview = mOverview
        this.mPosterPath = mPosterPath
        this.mTitle = mTitle
        this.mVoteAverage = mVoteAverage
    }

    constructor()

    @PrimaryKey
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
