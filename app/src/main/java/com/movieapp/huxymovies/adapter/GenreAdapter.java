package com.movieapp.huxymovies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {

    private List<Genre> genreList;

    public GenreAdapter(List<Genre> genres) {
        this.genreList = genres;
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genre,
                viewGroup, false);
        return new GenreHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder genreHolder, int i) {

        final Genre dm = genreList.get(i);
        genreHolder.genre_txt.setText(dm.getName());
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    static final class GenreHolder extends RecyclerView.ViewHolder

    {

        private TextView genre_txt;

        private GenreHolder(View itemView) {
            super(itemView);
            genre_txt = itemView.findViewById(R.id.genre_txt);
        }
    }
}
