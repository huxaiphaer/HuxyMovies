package com.movieapp.huxymovies.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.adapter.GenreAdapter
import com.movieapp.huxymovies.adapter.ResultAdapter.Companion.MOVIE_ID
import com.movieapp.huxymovies.adapter.ResultAdapter.Companion.MOVIE_NAME
import com.movieapp.huxymovies.adapter.ResultAdapter.Companion.MOVIE_OVERVIEW
import com.movieapp.huxymovies.databinding.ActivityDetailsBinding
import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.utils.NetworkUtility
import com.movieapp.huxymovies.utils.Utils
import com.movieapp.huxymovies.viewmodel.DetailViewModel


class DetailsActivity : AppCompatActivity() {

    private var i: Intent? = null
    private lateinit var activityDetailsBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        i = intent
        if (i != null) {
            toolbar.title = i!!.getStringExtra(MOVIE_NAME)
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val networkUtility = NetworkUtility(this@DetailsActivity)
        if (!networkUtility.isOnline) {
            Toast.makeText(this@DetailsActivity, "Please check your internet connection.",
                    Toast.LENGTH_LONG).show()
        } else {

            loadDetails()
        }

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

        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * This method is capable for loading data in from the View Model.
     */
    private fun loadDetails() {

        i = intent
        val movieId = i!!.getStringExtra(MOVIE_ID).trim { it <= ' ' }
        val movieIDParser = java.lang.Long.parseLong(movieId)

        // Initialize the ViewModel to set data to the UI.
        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel.getHeroes(movieIDParser).observe(this, Observer { detailsModal ->

            populateViews(detailsModal!!)

        })

    }

    /**
     * This method is the one which sets the data from the View Model
     *
     * @param detailsModal This is the param for the Model Object
     */

    private fun populateViews(detailsModal: DetailsModal) {

        // Setting the Movie Poster Image
        val toolbarImage = activityDetailsBinding.toolbarImg
        Glide.with(this@DetailsActivity)
                .load(Utils.IMAGE_BASE_URL + detailsModal.mPosterPath)
                .into(toolbarImage)

        // Setting data OverView.
        activityDetailsBinding.layoutContent!!.overviewTxt.text = i!!.getStringExtra(MOVIE_OVERVIEW)
        activityDetailsBinding.layoutContent!!.releaseDateTxt.text = detailsModal.mReleaseDate
        activityDetailsBinding.layoutContent!!.averageDetailsTxt.text = detailsModal.mVoteAverage.toString()

        // Setting a RatingBar
        val ratingBar = activityDetailsBinding.layoutContent!!.ratingBarDetails
        val stars = ratingBar.progressDrawable as LayerDrawable
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP)
        val roundVal = Math.round(detailsModal.mVoteAverage!!).toInt()
        ratingBar.numStars = roundVal


        //Set the RecyclerView.
        val recyclerView = activityDetailsBinding.layoutContent!!.genreRv
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        val genreAdapter = GenreAdapter(detailsModal.mGenres!!)
        recyclerView.adapter = genreAdapter


    }
}
