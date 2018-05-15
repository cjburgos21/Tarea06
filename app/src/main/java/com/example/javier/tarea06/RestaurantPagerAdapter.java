package com.example.javier.tarea06;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPagerAdapter extends FragmentPagerAdapter {
    Context mcontext;
    List<String> titleList;
    List<Fragment> fragmentList;

    public RestaurantPagerAdapter(FragmentManager fragm, Context mcontext){
        super(fragm);
        this.mcontext = mcontext;
        this.titleList = new ArrayList<>();
        this.fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position){
        return fragmentList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return titleList.get(position);
    }

    public void addItem(String title,Fragment frag){
        titleList.add(title);
        fragmentList.add(frag);
    }
}
