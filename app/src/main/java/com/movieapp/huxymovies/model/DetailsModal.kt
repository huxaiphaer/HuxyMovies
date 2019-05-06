package com.movieapp.huxymovies.model

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.movieapp.huxymovies.utils.Utils

class DetailsModal {


    @SerializedName("genres")
    var mGenres: List<Genre>? = null
    @SerializedName("poster_path")
    var mPosterPath: String? = null
    @SerializedName("release_date")
    var mReleaseDate: String? = null
    @SerializedName("vote_average")
    var mVoteAverage: Double? = null
    @SerializedName("overview")
    var mOverview: String? = null

    companion object {

        @JvmStatic
        @BindingAdapter("movieImage")
        fun LoadImage(view: View, mPosterPath: String?) {

            val imageView = view as ImageView
            Glide.with(view.context)
                    .load(Utils.IMAGE_BASE_URL + mPosterPath)
                    .into(imageView)

        }
    }

}
