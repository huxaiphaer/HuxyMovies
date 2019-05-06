package com.movieapp.huxymovies.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.databinding.ItemActivitymainBinding
import com.movieapp.huxymovies.model.Result
import com.movieapp.huxymovies.views.DetailsActivity

class ResultAdapter(private val context: Context) : PagedListAdapter<Result, ResultAdapter.ResultViewHolder>(DIFF_CALLBACK) {

    lateinit var mBinding: ItemActivitymainBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {


        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_activitymain, parent, false)

        return ResultViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {

        val result = getItem(position)

        if (result != null) {

            holder.activitymainBinding.movie = result
            holder.activitymainBinding.ratingBar.rating = result.mVoteAverage!!.toFloat()
            holder.activitymainBinding.titleTxt.text = result.mTitle
            holder.activitymainBinding.averageTxt.text = result.mVoteAverage.toString()

            holder.root!!.setOnClickListener {
                val i = Intent(this@ResultAdapter.context, DetailsActivity::class.java)
                i.putExtra(MOVIE_ID, result.mId.toString())
                i.putExtra(MOVIE_NAME, result.mTitle)
                i.putExtra(MOVIE_OVERVIEW, result.mOverview)
                context.startActivity(i)
            }
        }

    }

    class ResultViewHolder(itemView: ItemActivitymainBinding) : RecyclerView.ViewHolder(itemView.root) {

        var activitymainBinding: ItemActivitymainBinding = itemView
        var root: View = itemView.root

    }

    companion object {

        const val MOVIE_ID = "MOVIE_ID"
        const val MOVIE_NAME = "MOVIE_NAME"
        const val MOVIE_OVERVIEW = "MOVIE_OVERVIEW"

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
