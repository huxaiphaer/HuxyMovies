package com.movieapp.huxymovies.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.utils.Utils

@Entity(tableName = "Results")
class Result {

    companion object {

        @JvmStatic
        @BindingAdapter("movieImage")
        fun LoadImage(view: View, mPosterPath: String?) {

            val imageView = view as ImageView
            Glide.with(view.context)
                    .load(Utils.IMAGE_BASE_URL + mPosterPath)
                    .into(imageView)

        }

        @JvmStatic
        @BindingAdapter("ratingValue")
        fun setRating(ratingBar: RatingBar, mVoteAverage: Double) {
            if (mVoteAverage != null) {
                ratingBar.rating = mVoteAverage.toFloat()
                val stars = ratingBar.progressDrawable as LayerDrawable
                stars.getDrawable(2).setColorFilter(ContextCompat.getColor(ratingBar.context, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP)
                val roundVal = Math.round(mVoteAverage!!)
                ratingBar.numStars = roundVal.toInt()
            }

        }
    }

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