
package com.movieapp.huxymovies.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MovieApiResponse {

    @SerializedName("page")
    public Long mPage;
    @SerializedName("results")
    public List<Result> mResults;
    @SerializedName("total_pages")
    public Long mTotalPages;
    @SerializedName("total_results")
    public Long mTotalResults;

}
