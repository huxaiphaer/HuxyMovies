package com.movieapp.huxymovies.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.adapter.ResultAdapter;
import com.movieapp.huxymovies.model.Result;
import com.movieapp.huxymovies.viewmodel.ResultViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource("Network_Call");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
    }

    private void loadData() {

        espressoTestIdlingResource.increment();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Calling the ViewModel
        ResultViewModel resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        final ResultAdapter resultAdapter = new ResultAdapter(this);


        resultViewModel.resultPagedList.observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(@Nullable PagedList<Result> results) {


                resultAdapter.submitList(results);
            }
        });

        recyclerView.setAdapter(resultAdapter);
        espressoTestIdlingResource.decrement();
    }

    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return espressoTestIdlingResource;
    }
}
