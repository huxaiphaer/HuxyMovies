package com.movieapp.huxymovies

import com.movieapp.huxymovies.model.DetailsModal
import com.movieapp.huxymovies.model.Genre
import com.movieapp.huxymovies.model.MovieApiResponse
import com.movieapp.huxymovies.model.Result

import junit.framework.TestCase

import org.junit.Assert

/**
 * This class tests all the project models
 */
class Models : TestCase() {

    lateinit var movieApiResponse: MovieApiResponse
    lateinit var detailsModal: DetailsModal
    lateinit var result: Result
    lateinit var genre: Genre

    @Throws(Exception::class)
    override fun setUp() {

        super.setUp()
        movieApiResponse = MovieApiResponse()
        detailsModal = DetailsModal()
        result = Result()
        genre = Genre()
    }

    fun testGetID() {
        val expected = Math.random().toInt()
        movieApiResponse.id = expected
        val actual = movieApiResponse.id!!
        Assert.assertEquals(expected.toLong(), actual.toLong())
    }

    fun testPageNumber() {
        val expected = Math.random().toInt().toLong()
        movieApiResponse.mPage = expected
        val actual = movieApiResponse.mPage!!
        Assert.assertEquals(expected, actual)
    }

    fun testTotalPages() {
        val expected = Math.random().toInt().toLong()
        movieApiResponse.mTotalPages = expected
        val actual = movieApiResponse.mTotalPages!!
        Assert.assertEquals(expected, actual)
    }

    fun testPosterPath() {

        val expected = "Hello"
        detailsModal.mPosterPath = expected
        val actual = detailsModal.mPosterPath
        Assert.assertEquals(expected, actual)
    }

    fun testReleaseDate() {

        val expected = "Date"
        detailsModal.mReleaseDate = expected
        val actual = detailsModal.mReleaseDate
        Assert.assertEquals(expected, actual)
    }

    fun testAverageRating() {

        val expected = 4.0
        detailsModal.mVoteAverage = expected
        val actual = detailsModal.mVoteAverage!!
        Assert.assertEquals(expected, actual, 4.0)
    }

    fun testGenreId() {
        val expected = Math.random().toLong()
        genre.id = expected
        val actual = genre.id!!
        Assert.assertEquals(expected, actual)
    }

    fun testGenreName() {
        val expected = "Genre"
        genre.name = expected
        val actual = genre.name
        Assert.assertEquals(expected, actual)
    }

}
