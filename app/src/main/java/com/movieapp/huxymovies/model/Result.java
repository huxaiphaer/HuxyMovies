package com.movieapp.huxymovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("adult")
    public Boolean mAdult;
    @SerializedName("backdrop_path")
    public String mBackdropPath;
    @SerializedName("genre_ids")
    public List<Long> mGenreIds;
    @SerializedName("id")
    public Long mId;
    @SerializedName("original_language")
    public String mOriginalLanguage;
    @SerializedName("original_title")
    public String mOriginalTitle;
    @SerializedName("overview")
    public String mOverview;
    @SerializedName("popularity")
    public Double mPopularity;
    @SerializedName("poster_path")
    public String mPosterPath;
    @SerializedName("release_date")
    public String mReleaseDate;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("video")
    public Boolean mVideo;
    @SerializedName("vote_average")
    public Double mVoteAverage;
    @SerializedName("vote_count")
    public Long mVoteCount;


}
