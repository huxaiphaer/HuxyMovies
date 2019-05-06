package com.movieapp.huxymovies.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.databinding.ItemGenreBinding
import com.movieapp.huxymovies.model.Genre

class GenreAdapter(private val genreList: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreHolder>() {


    private lateinit var mBinding: ItemGenreBinding
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GenreHolder {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.item_genre, viewGroup, false)

        return GenreHolder(mBinding)
    }

    override fun onBindViewHolder(genreHolder: GenreHolder, i: Int) {

        val dm = genreList[i]
        genreHolder.itemGenreBinding.genre = dm
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    class GenreHolder constructor(itemView: ItemGenreBinding) : RecyclerView.ViewHolder(itemView.root) {

        var itemGenreBinding: ItemGenreBinding = itemView

    }
}
