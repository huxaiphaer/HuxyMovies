package com.movieapp.huxymovies.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.adapter.GenreAdapter;
import com.movieapp.huxymovies.model.DetailsModal;
import com.movieapp.huxymovies.utils.NetworkUtility;
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
        setContentView(R.layout.activity_details);
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

                NetworkUtility networkUtility = new NetworkUtility(DetailsActivity.this);

                if (!networkUtility.isOnline()) {
                    Toast.makeText(DetailsActivity.this, "Please check your internet connection.",
                            Toast.LENGTH_LONG).show();

                } else {
                    populateViews(detailsModal);
                }
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
                .load(Utils.INSTANCE.getIMAGE_BASE_URL().concat(detailsModal.getMPosterPath()))
                .into(toolbar_image);

        // Setting the OverView.
        TextView overview_txt = findViewById(R.id.overview_txt);
        overview_txt.setText(i.getStringExtra(MOVIE_OVERVIEW));

        //Setting the rating bar.
        RatingBar rating_bar = findViewById(R.id.rating_bar_details);
        LayerDrawable stars = (LayerDrawable) rating_bar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP);
        int roundVal = (int) Math.round(detailsModal.getMVoteAverage());
        rating_bar.setNumStars(roundVal);

        TextView average_txt = findViewById(R.id.average_details_txt);
        average_txt.setText(String.valueOf(roundVal));

        //Setting the release date.
        TextView release_date__txt = findViewById(R.id.release_date__txt);
        release_date__txt.setText(detailsModal.getMReleaseDate());


        //Set the Recyclerview.
        RecyclerView recyclerView = findViewById(R.id.genre_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GenreAdapter genreAdapter = new GenreAdapter(detailsModal.getMGenres());
        recyclerView.setAdapter(genreAdapter);


    }
}
