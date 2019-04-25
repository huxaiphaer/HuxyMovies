package com.movieapp.huxymovies;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailsActivity> activity = new ActivityTestRule<>(DetailsActivity.class);

    @Test
    public void ShowTheViews() {

        Espresso.onView(withId(R.id.imageView))
                .check(ViewAssertions.matches((isDisplayed())));


    }
}
