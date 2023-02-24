package com.erenalparslan.spacexapijava.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.erenalparslan.spacexapijava.View.CapsuleFragment;
import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.View.CompanyFragment;
import com.erenalparslan.spacexapijava.View.CoreFragment;
import com.erenalparslan.spacexapijava.View.RocketFragment;
import com.erenalparslan.spacexapijava.View.ShipFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
                return  CompanyFragment.newInstance();
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