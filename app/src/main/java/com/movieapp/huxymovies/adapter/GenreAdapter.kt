package com.movieapp.huxymovies.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.model.Genre

class GenreAdapter(private val genreList: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GenreHolder {
        val layoutView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_genre,
                viewGroup, false)
        return GenreHolder(layoutView)
    }

    override fun onBindViewHolder(genreHolder: GenreHolder, i: Int) {

        val dm = genreList[i]
        genreHolder.genreTxt.text = dm.name
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    class GenreHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var genreTxt: TextView = itemView.findViewById(R.id.genre_txt)

        init {
            genreTxt = itemView.findViewById(R.id.genre_txt)
        }
    }
}
