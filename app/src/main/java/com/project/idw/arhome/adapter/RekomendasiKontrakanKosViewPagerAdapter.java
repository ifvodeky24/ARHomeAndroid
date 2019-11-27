package com.project.idw.arhome.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RekomendasiKontrakanKosViewPagerAdapter extends FragmentPagerAdapter{
    private final List<Fragment> mFragmenList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public RekomendasiKontrakanKosViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmenList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentTitleList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mFragmenList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
