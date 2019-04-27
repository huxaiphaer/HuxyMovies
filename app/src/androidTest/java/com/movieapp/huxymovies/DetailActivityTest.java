package com.movieapp.huxymovies;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movieapp.huxymovies.views.DetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest{

    /*@Rule
    public ActivityTestRule<DetailsActivity> activity =
            new ActivityTestRule<DetailsActivity>(DetailsActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Context targetContext = InstrumentationRegistry.getInstrumentation()
                            .getTargetContext();
                    Intent result = new Intent(targetContext, DetailsActivity.class);
                    result.putExtra(MOVIE_ID, "86311");
                    return result;
                }
            };*/
    @Rule
    public ActivityTestRule<DetailsActivity> activityRule =
            new ActivityTestRule<>(DetailsActivity.class,false,false);

    @Test
    public void ShowTheViews() {

        Espresso.onView(withId(R.id.toolbar_img))
                .check(matches(isDisplayed()));

    }
}
