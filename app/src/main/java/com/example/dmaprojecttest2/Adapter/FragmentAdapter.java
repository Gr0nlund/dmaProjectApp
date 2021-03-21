package com.example.dmaprojecttest2.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;

    public FragmentAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    public Fragment getItem(int position) {
        if(position == 0) {
            return MapsFragment.getINSTANCE();
        }
        else if(position == 1){
            return MenuFragment.getINSTANCE();
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "MapsFragment";
            case 1:
                return "MenuFragment";
        }
            return "";
    }
}
