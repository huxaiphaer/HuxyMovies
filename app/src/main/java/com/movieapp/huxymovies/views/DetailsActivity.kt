package com.movieapp.huxymovies.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.adapter.GenreAdapter
import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.utils.NetworkUtility
import com.movieapp.huxymovies.utils.Utils
import com.movieapp.huxymovies.viewmodel.DetailViewModel

import com.movieapp.huxymovies.adapter.ResultAdapter.Companion

class DetailsActivity : AppCompatActivity() {

    private var i: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        i = intent
        if (i != null) {
            toolbar.title = i!!.getStringExtra(Companion.MOVIE_NAME)
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        loadDetails()
    }


    /**
     * This method is helping us to trigger the back button.
     *
     * @param item This is the menuitem.
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> onBackPressed()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * This method is capable for loading data in from the View Model.
     */
    private fun loadDetails() {

        i = intent
        val movie_id = i!!.getStringExtra(Companion.MOVIE_ID).trim { it <= ' ' }
        val MovieID = java.lang.Long.parseLong(movie_id)

        // Initialize the ViewModel to set data to the UI.
        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel.getHeroes(MovieID).observe(this, Observer { detailsModal ->
            val networkUtility = NetworkUtility(this@DetailsActivity)

            if (!networkUtility.isOnline) {
                Toast.makeText(this@DetailsActivity, "Please check your internet connection.",
                        Toast.LENGTH_LONG).show()

            } else {
                populateViews(detailsModal!!)
            }
        })

    }

    /**
     * This method is the one which sets the data from the View Model
     *
     * @param detailsModal This is the param for the Model Object
     */
    private fun populateViews(detailsModal: DetailsModal) {

        // Setting the Movie Poster Image
        val toolbar_image = findViewById<ImageView>(R.id.toolbar_img)
        Glide.with(this@DetailsActivity)
                .load(Utils.IMAGE_BASE_URL + detailsModal.mPosterPath)
                .into(toolbar_image)

        // Setting the OverView.
        val overview_txt = findViewById<TextView>(R.id.overview_txt)
        overview_txt.text = i!!.getStringExtra(Companion.MOVIE_OVERVIEW)

        //Setting the rating bar.
        val rating_bar = findViewById<RatingBar>(R.id.rating_bar_details)
        val stars = rating_bar.progressDrawable as LayerDrawable
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP)
        val roundVal = Math.round(detailsModal.mVoteAverage!!).toInt()
        rating_bar.numStars = roundVal

        val average_txt = findViewById<TextView>(R.id.average_details_txt)
        average_txt.text = roundVal.toString()

        //Setting the release date.
        val release_date__txt = findViewById<TextView>(R.id.release_date__txt)
        release_date__txt.text = detailsModal.mReleaseDate


        //Set the Recyclerview.
        val recyclerView = findViewById<RecyclerView>(R.id.genre_rv)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val genreAdapter = GenreAdapter(detailsModal.mGenres!!)
        recyclerView.adapter = genreAdapter


    }
}
