package com.kelompokempat.githubapi.adapter;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kelompokempat.githubapi.model.search.ModelSearchData;
import com.kelompokempat.githubapi.fragment.FragmentFollowers;
import com.kelompokempat.githubapi.fragment.FragmentFollowing;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    ModelSearchData modelSearchData;

    public ViewPagerAdapter(FragmentManager fragmentManager, ModelSearchData modelSearchData) {
        super(fragmentManager);
        this.modelSearchData = modelSearchData;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchData", (Parcelable) modelSearchData);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentFollowers();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentFollowing();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Followers";
                break;
            case 1:
                title = "Following";
                break;
        }
        return title;
    }

}
