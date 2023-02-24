package com.erenalparslan.spacexapijava.View;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erenalparslan.spacexapijava.ui.main.SectionsPagerAdapter;
import com.erenalparslan.spacexapijava.databinding.ActivitySpaceXBinding;

public class SpaceX extends AppCompatActivity {

    private ActivitySpaceXBinding binding;

    // TODO: 2/24/2023 I will add search to this application. 
    // TODO: 2/24/2023 I will write by removing adapters.




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySpaceXBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coded by Eren Alparslan.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}