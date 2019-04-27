package com.movieapp.huxymovies.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.adapter.ResultAdapter;
import com.movieapp.huxymovies.model.Result;
import com.movieapp.huxymovies.utils.NetworkUtility;
import com.movieapp.huxymovies.viewmodel.ResultViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    /**
     * This method helps to get data from the ViewModel.
     */
    private void loadData() {

        //Show the progressbar.
        progressBar = findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Calling the ViewModel
        ResultViewModel resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        final ResultAdapter resultAdapter = new ResultAdapter(this);

        resultViewModel.resultPagedList.observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> results) {

                NetworkUtility networkUtility = new NetworkUtility(MainActivity.this);

                if (!networkUtility.isOnline()) {

                    Toast.makeText(MainActivity.this, "Please check your internet connection.",
                            Toast.LENGTH_LONG).show();

                    // Turn off the Progressbar.
                    progressBar = findViewById(R.id.pb);
                    progressBar.setVisibility(View.INVISIBLE);

                } else {

                    if (results != null) {

                        resultAdapter.submitList(results);

                        // Add animation to the RecyclerView.
                        final Context context = recyclerView.getContext();
                        final LayoutAnimationController controller =
                                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_right);

                        recyclerView.setLayoutAnimation(controller);
                        recyclerView.getAdapter().notifyDataSetChanged();
                        recyclerView.scheduleLayoutAnimation();

                        // Turn off the Progressbar.
                        progressBar = findViewById(R.id.pb);
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {

                        // Turn off the Progressbar.
                        progressBar = findViewById(R.id.pb);
                        progressBar.setVisibility(View.INVISIBLE);

                        Toast.makeText(MainActivity.this, "There are no movies , check your internet connection",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        recyclerView.setAdapter(resultAdapter);
    }

}
