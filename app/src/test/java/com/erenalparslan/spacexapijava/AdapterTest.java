package com.erenalparslan.spacexapijava;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.erenalparslan.spacexapijava.View.SpaceX;
import com.erenalparslan.spacexapijava.View.fragments.CapsuleFragment;
import com.erenalparslan.spacexapijava.View.fragments.CompanyFragment;
import com.erenalparslan.spacexapijava.View.fragments.CoreFragment;
import com.erenalparslan.spacexapijava.View.fragments.RocketFragment;
import com.erenalparslan.spacexapijava.View.fragments.ShipFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdapterTest {
    @Mock
    private FragmentManager mockFragmentManager;

    @Mock
    private Context mockContext;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItemRocket() {
        SpaceX.SectionsPagerAdapter sectionsPagerAdapter = new SpaceX.SectionsPagerAdapter(mockContext, mockFragmentManager);
        Fragment fragment = sectionsPagerAdapter.getItem(0);
        assertNotNull(fragment);
        assertTrue(fragment instanceof RocketFragment);
    }

    @Test
    public void testGetItemCapsule() {
        SpaceX.SectionsPagerAdapter sectionsPagerAdapter = new SpaceX.SectionsPagerAdapter(mockContext, mockFragmentManager);
        Fragment fragment = sectionsPagerAdapter.getItem(1);
        assertNotNull(fragment);
        assertTrue(fragment instanceof CapsuleFragment);
    }

    @Test
    public void testGetItemShip() {
        SpaceX.SectionsPagerAdapter sectionsPagerAdapter = new SpaceX.SectionsPagerAdapter(mockContext, mockFragmentManager);
        Fragment fragment = sectionsPagerAdapter.getItem(2);
        assertNotNull(fragment);
        assertTrue(fragment instanceof ShipFragment);
    }

    @Test
    public void testGetItemCore() {
        SpaceX.SectionsPagerAdapter sectionsPagerAdapter = new SpaceX.SectionsPagerAdapter(mockContext, mockFragmentManager);
        Fragment fragment = sectionsPagerAdapter.getItem(3);
        assertNotNull(fragment);
        assertTrue(fragment instanceof CoreFragment);
    }

    @Test
    public void testGetItemCompany() {
        SpaceX.SectionsPagerAdapter sectionsPagerAdapter = new SpaceX.SectionsPagerAdapter(mockContext, mockFragmentManager);
        Fragment fragment = sectionsPagerAdapter.getItem(4);
        assertNotNull(fragment);
        assertTrue(fragment instanceof CompanyFragment);
    }
}