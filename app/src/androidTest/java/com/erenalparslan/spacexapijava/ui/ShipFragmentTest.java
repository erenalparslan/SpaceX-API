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

public class ShipFragmentTest {



    @Before
    public void setup() throws InterruptedException {


        ActivityScenario.launch(SpaceX.class);

        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(1000);

    }

    @Test
    public void ShipFragmentDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.shipRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void ShipItemClickThenDisplayDetailFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.shipRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.shipConstraint)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.prop1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.prop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void ShipRecyclerSwipe() {
        Espresso.onView(ViewMatchers.withId(R.id.shipRecyclerView)).perform( ViewActions.swipeUp());
    }

    @Test
    public void ShipRecyclerDelete() {
        Espresso.onView(ViewMatchers.withId(R.id.shipRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.swipeRight()));
    }

}
