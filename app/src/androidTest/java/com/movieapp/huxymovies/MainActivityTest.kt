package com.movieapp.huxymovies

import android.content.pm.ActivityInfo
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.movieapp.huxymovies.views.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This instrumentation class tests both
 * the Main and Details Activity.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun screenOrientationAndRecyclerViewScrolling() {

        sleep()

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        activity.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        sleep()

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(11))

        activity
                .activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    }

    @Test
    fun scrollOtherPagesOfTheMoviesList() {

        sleep()
        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(30))

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

    }

    @Test
    fun launchDetailsActivity() {

        sleep()
        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))

        Espresso.onView(withId(R.id.recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }


    private fun sleep() {

        try {
            Thread.sleep(6000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }


}
