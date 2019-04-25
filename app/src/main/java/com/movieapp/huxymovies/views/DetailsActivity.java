package com.movieapp.huxymovies.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.model.DetailsModal;
import com.movieapp.huxymovies.utils.Utils;
import com.movieapp.huxymovies.viewmodel.DetailViewModel;

import static com.movieapp.huxymovies.adapter.ResultAdapter.MOVIE_ID;
import static com.movieapp.huxymovies.adapter.ResultAdapter.MOVIE_NAME;
import static com.movieapp.huxymovies.adapter.ResultAdapter.MOVIE_OVERVIEW;

public class DetailsActivity extends AppCompatActivity {

    private Intent i = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        i = getIntent();
        if (i != null) {
            toolbar.setTitle(i.getStringExtra(MOVIE_NAME));
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadDetails();
    }


    /**
     * This method is helping us to trigger the back button.
     *
     * @param item This is the menuitem.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * This method is capable for loading data in from the View Model.
     */
    private void loadDetails() {

        i = getIntent();
        String movie_id = i.getStringExtra(MOVIE_ID).trim();
        long MovieID = Long.parseLong(movie_id);

        // Initialize the ViewModel to set data to the UI.
        DetailViewModel
                detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.getHeroes(MovieID).observe(this, new Observer<DetailsModal>() {
            @Override
            public void onChanged(@Nullable DetailsModal detailsModal) {

                populateViews(detailsModal);
            }
        });

    }

    /**
     * This method is the one which sets the data from the View Model
     *
     * @param detailsModal This is the param for the Model Object
     */
    private void populateViews(DetailsModal detailsModal) {

        // Setting the Movie Poster Image
        ImageView toolbar_image = findViewById(R.id.toolbar_img);
        Glide.with(DetailsActivity.this)
                .load(Utils.IMAGE_BASE_URL.concat(detailsModal.mPosterPath))
                .into(toolbar_image);

        // Setting the OverView.
        TextView overview_txt = findViewById(R.id.overview_txt);
        overview_txt.setText(i.getStringExtra(MOVIE_OVERVIEW));


    }
}
