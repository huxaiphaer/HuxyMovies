package com.movieapp.huxymovies;

import com.movieapp.huxymovies.model.DetailsModal;
import com.movieapp.huxymovies.model.Genre;
import com.movieapp.huxymovies.model.MovieApiResponse;
import com.movieapp.huxymovies.model.Result;

import junit.framework.TestCase;

import org.junit.Assert;

/**
 * This class tests all the project models
 */
public class Models extends TestCase {

    MovieApiResponse movieApiResponse;
    DetailsModal detailsModal;
    Result result;
    Genre genre;

    protected void setUp() throws Exception {

        super.setUp();
        movieApiResponse = new MovieApiResponse();
        detailsModal = new DetailsModal("");
        result = new Result();
        genre = new Genre();
    }

    public void testGetID() {
        int expected = (int) Math.random();
        movieApiResponse.setId(expected);
        int actual = movieApiResponse.getId();
        Assert.assertEquals(expected, actual);
    }

    public void testPageNumber() {
        long expected = (int) Math.random();
        movieApiResponse.setMPage(expected);
        long actual = movieApiResponse.getMPage();
        Assert.assertEquals(expected, actual);
    }

    public void testTotalPages() {
        long expected = (int) Math.random();
        movieApiResponse.setMTotalPages(expected);
        long actual = movieApiResponse.getMTotalPages();
        Assert.assertEquals(expected, actual);
    }

    public void testPosterPath(){

        String  expected = "Hello";
        detailsModal.setMPosterPath(expected);
        String  actual = detailsModal.getMPosterPath();
        Assert.assertEquals(expected, actual);
    }

    public void testReleaseDate(){

        String  expected = "Date";
        detailsModal.setMReleaseDate(expected);
        String  actual = detailsModal.getMReleaseDate();
        Assert.assertEquals(expected, actual);
    }

    public void testAverageRating(){

        double  expected = 4.0;
        detailsModal.setMVoteAverage(expected);
        double  actual = detailsModal.getMVoteAverage();
        Assert.assertEquals(expected, actual,4.0);
    }

    public void testGenreId(){
        long expected = (long) Math.random();
        genre.setId(expected);
        long actual = genre.getId();
        Assert.assertEquals(expected, actual);
    }

    public void testGenreName(){
        String  expected = "Genre";
        genre.setName(expected);
        String  actual = genre.getName();
        Assert.assertEquals(expected, actual);
    }

}
