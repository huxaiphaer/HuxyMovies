package com.movieapp.huxymovies;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movieapp.huxymovies.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This instrumentation class tests both
 * the Main and Details Activity.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void screenOrientationAndRecyclerViewScrolling() {

        sleep();

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(3));
        activity.
                getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        sleep();

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(11));

        activity
                .getActivity()
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    @Test
    public void scrollOtherPagesOfTheMoviesList() {

        sleep();
        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(30));

        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(10));

    }

    @Test
    public void launchDetailsActivity() {

        sleep();
        Espresso.onView(withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollToPosition(3));

        Espresso.onView(withId(R.id.recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }


    private void sleep() {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
