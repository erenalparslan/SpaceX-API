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

public class CapsuleFragmentTest {
    @Before
    public void setup() throws InterruptedException {


        ActivityScenario.launch(SpaceX.class);

        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft());
        Thread.sleep(1000);

    }

    @Test
    public void CapsuleFragmentDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.capsuleRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void  CapsuleItemClickThenDisplayDetailFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.capsuleRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(5, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.prop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.prop1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.prop2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void CapsuleRecyclerSwipe() {
        Espresso.onView(ViewMatchers.withId(R.id.capsuleRecyclerView)).perform( ViewActions.swipeUp());
    }

    @Test
    public void  CapsuleRecyclerDelete() {
        Espresso.onView(ViewMatchers.withId(R.id.capsuleRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.swipeRight()));
    }

}
