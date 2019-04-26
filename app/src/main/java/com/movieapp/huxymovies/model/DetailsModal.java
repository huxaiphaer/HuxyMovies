
package com.movieapp.huxymovies.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DetailsModal {

    @SerializedName("adult")
    public Boolean mAdult;
    @SerializedName("backdrop_path")
    public String mBackdropPath;
    @SerializedName("belongs_to_collection")
    public BelongsToCollection mBelongsToCollection;
    @SerializedName("budget")
    public Long mBudget;
    @SerializedName("genres")
    public List<Genre> mGenres;
    @SerializedName("homepage")
    public String mHomepage;
    @SerializedName("id")
    public Long mId;
    @SerializedName("imdb_id")
    public String mImdbId;
    @SerializedName("original_language")
    public String mOriginalLanguage;
    @SerializedName("original_title")
    public String mOriginalTitle;

    public String overview;
    @SerializedName("popularity")
    public Double mPopularity;
    @SerializedName("poster_path")
    public String mPosterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries;
    @SerializedName("release_date")
    public String mReleaseDate;
    @SerializedName("revenue")
    private Long mRevenue;
    @SerializedName("runtime")
    private Long mRuntime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> mSpokenLanguages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    public Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;


    public DetailsModal(String mTitle) {
        this.mTitle = mTitle;
    }
}
