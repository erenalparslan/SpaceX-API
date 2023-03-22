package com.erenalparslan.spacexapijava.ui;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.View.SpaceX;

import org.junit.Before;
import org.junit.Test;

public class CoreFragmentTest {
    @Before
    public void setup() throws InterruptedException {


        ActivityScenario.launch(SpaceX.class);

        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(1000);

    }

    @Test
    public void CoreFragmentDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerCore)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void CoreItemClickThenDisplayDetailFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerCore)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.constraintCore)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

    @Test
    public void CoreRecyclerSwipe() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerCore)).perform( ViewActions.swipeUp());
    }

    @Test
    public void CoreRecyclerDelete() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerCore)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.swipeRight()));
    }
}
