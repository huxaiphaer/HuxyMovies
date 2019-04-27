package com.movieapp.huxymovies.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.model.Result
import com.movieapp.huxymovies.utils.Utils
import com.movieapp.huxymovies.views.DetailsActivity

class ResultAdapter(private val context: Context) : PagedListAdapter<Result, ResultAdapter.ResultViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_activitymain, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {

        val result = getItem(position)


        if (result != null) {

            Glide.with(context)
                    .load(Utils.IMAGE_BASE_URL + result.mPosterPath)
                    .into(holder.movie_img)
            holder.title_txt.text = result.mTitle
            holder.average_txt.text = result.mVoteAverage.toString()

            // Style the rating bar.
            val stars = holder.rating_bar.progressDrawable as LayerDrawable
            stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this@ResultAdapter.context, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP)
            val roundVal = Math.round(result.mVoteAverage!!).toInt()
            holder.rating_bar.numStars = roundVal



            holder.root.setOnClickListener {
                val i = Intent(this@ResultAdapter.context, DetailsActivity::class.java)
                i.putExtra(MOVIE_ID, result.mId.toString())
                i.putExtra(MOVIE_NAME, result.mTitle)
                i.putExtra(MOVIE_OVERVIEW, result.mOverview)
                context.startActivity(i)
            }
        }

    }

     inner class ResultViewHolder(var root: View) : RecyclerView.ViewHolder(root) {

        var movie_img: ImageView
        var title_txt: TextView
        var average_txt: TextView
        var rating_bar: RatingBar


        init {
            movie_img = root.findViewById(R.id.imageView)
            title_txt = root.findViewById(R.id.title_txt)
            average_txt = root.findViewById(R.id.average_txt)
            rating_bar = root.findViewById(R.id.rating_bar)
        }

    }

    companion object {

        val MOVIE_ID = "MOVIE_ID"
        val MOVIE_NAME = "MOVIE_NAME"
        val MOVIE_OVERVIEW = "MOVIE_OVERVIEW"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.mId === newItem.mId
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}
