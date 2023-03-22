package com.erenalparslan.spacexapijava.ui;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import com.erenalparslan.spacexapijava.View.SpaceX;
import com.erenalparslan.spacexapijava.R;
import org.junit.Before;
import org.junit.Test;

public class RocketFragmentTest {

    @Before
    public void setup() {


        ActivityScenario.launch(SpaceX.class);

    }

    @Test
    public void RocketFragmentDisplay() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerRocket)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void RocketItemClickThenDisplayDetailFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerRocket)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.prop1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    @Test
    public void RocketRecyclerSwipe() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerRocket)).perform(ViewActions.swipeUp());
    }

    @Test
    public void RocketRecyclerDelete() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerRocket)).perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.swipeRight()));
    }


}
