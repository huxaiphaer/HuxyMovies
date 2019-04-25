package com.movieapp.huxymovies.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movieapp.huxymovies.R;
import com.movieapp.huxymovies.model.Result;
import com.movieapp.huxymovies.utils.Utils;
import com.movieapp.huxymovies.views.DetailsActivity;

public class ResultAdapter extends PagedListAdapter<Result, ResultAdapter.ResultViewHolder> {

    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_NAME = "MOVIE_NAME";
    public static final String MOVIE_OVERVIEW = "MOVIE_OVERVIEW";

    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.mId == newItem.mId;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Result oldItem, @NonNull Result newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context context;


    public ResultAdapter(Context mContext) {

        super(DIFF_CALLBACK);

        this.context = mContext;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_activitymain, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Result result = getItem(position);


        if (result != null) {

            Glide.with(context)
                    .load(Utils.IMAGE_BASE_URL.concat(result.mPosterPath))
                    .into(holder.movie_img);
            holder.title_txt.setText(result.mTitle);
            holder.average_txt.setText(String.valueOf(result.mVoteAverage));

            // Style the rating bar.
            LayerDrawable stars = (LayerDrawable) holder.rating_bar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(ContextCompat.getColor(ResultAdapter.this.context, R.color.rating_bar), PorterDuff.Mode.SRC_ATOP);
            int roundVal= (int) Math.round(result.mVoteAverage);
            holder.rating_bar.setNumStars(roundVal);



            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ResultAdapter.this.context, DetailsActivity.class);
                    i.putExtra(MOVIE_ID, String.valueOf(result.mId));
                    i.putExtra(MOVIE_NAME, result.mTitle);
                    i.putExtra(MOVIE_OVERVIEW,result.mOverview);
                    context.startActivity(i);

                }
            });
        }

    }

    class ResultViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_img;
        TextView title_txt;
        TextView average_txt;
        RatingBar rating_bar;
        View root;


        public ResultViewHolder(View itemView) {
            super(itemView);

            root = itemView;
            movie_img = itemView.findViewById(R.id.imageView);
            title_txt = itemView.findViewById(R.id.title_txt);
            average_txt = itemView.findViewById(R.id.average_txt);
            rating_bar = itemView.findViewById(R.id.rating_bar);
        }

    }
}
