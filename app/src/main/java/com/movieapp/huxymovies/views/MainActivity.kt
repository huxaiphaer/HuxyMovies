package com.movieapp.huxymovies.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import com.movieapp.huxymovies.R
import com.movieapp.huxymovies.adapter.ResultAdapter
import com.movieapp.huxymovies.utils.NetworkUtility
import com.movieapp.huxymovies.viewmodel.ResultViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    /**
     * This method helps to get data from the ViewModel.
     */
    private fun loadData() {

        progressBar = findViewById(R.id.pb)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Show the progressbar.
        progressBar.visibility = View.VISIBLE

        // Calling the ViewModel
        val resultViewModel = ViewModelProviders.of(this).get(ResultViewModel::class.java)
        val resultAdapter = ResultAdapter(this)

        resultViewModel.resultPagedList.observe(this, Observer { results ->
            val networkUtility = NetworkUtility(this@MainActivity)

            if (!networkUtility.isOnline) {

                Toast.makeText(this@MainActivity, "Please check your internet connection.",
                        Toast.LENGTH_LONG).show()

                // Turn off the Progressbar.
                progressBar = findViewById(R.id.pb)
                progressBar.visibility = View.INVISIBLE

            } else {

                if (results != null) {

                    //Show the progressbar.

                    progressBar.visibility = View.VISIBLE
                    resultAdapter.submitList(results)

                    // Add animation to the RecyclerView.
                    val context = recyclerView.context
                    val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right)

                    recyclerView.layoutAnimation = controller
                    recyclerView.adapter!!.notifyDataSetChanged()
                    recyclerView.scheduleLayoutAnimation()

                    // Turn off the Progressbar.
                    progressBar = findViewById(R.id.pb)
                    progressBar.visibility = View.INVISIBLE
                } else {

                    // Turn off the Progressbar.
                    progressBar = findViewById(R.id.pb)
                    progressBar.visibility = View.INVISIBLE

                    Toast.makeText(this@MainActivity, "There are no movies , check your internet connection",
                            Toast.LENGTH_LONG).show()
                }
            }
        })

        recyclerView.adapter = resultAdapter
    }

}
