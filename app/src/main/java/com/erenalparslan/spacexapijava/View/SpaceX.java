package com.erenalparslan.spacexapijava.View;

import android.content.Context;
import android.os.Bundle;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.View.fragments.CapsuleFragment;
import com.erenalparslan.spacexapijava.View.fragments.CompanyFragment;
import com.erenalparslan.spacexapijava.View.fragments.CoreFragment;
import com.erenalparslan.spacexapijava.View.fragments.RocketFragment;
import com.erenalparslan.spacexapijava.View.fragments.ShipFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.arch.core.util.Function;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

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

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_4, R.string.tab_text_3, R.string.tab_text_5};
        private final Context mContext;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {


            switch (position) {
                case 0:
                    return RocketFragment.newInstance();
                case 1:
                    return CapsuleFragment.newInstance();
                case 2:
                    return ShipFragment.newInstance();
                case 3:
                    return CoreFragment.newInstance();
                case 4:
                    return CompanyFragment.newInstance();
                default:
                    return null;
            }

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getResources().getString(TAB_TITLES[position]);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 5;
        }
    }

    public static class PageViewModel extends ViewModel {

        private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
        private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return "Hello world from section: " + input;
            }
        });

        public void setIndex(int index) {
            mIndex.setValue(index);
        }

        public LiveData<String> getText() {
            return mText;
        }
    }
}