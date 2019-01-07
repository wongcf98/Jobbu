package com.example.scollex.jobbu;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> homeFragment = new ArrayList<>();
    private final List<String> jobs = new ArrayList<>();


    ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return homeFragment.get(position);
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return jobs.get(position);
    }

    public void AddHomeFragment (Fragment fragment, String jobsname){
        homeFragment.add(fragment);
        jobs.add(jobsname);
    }
}
