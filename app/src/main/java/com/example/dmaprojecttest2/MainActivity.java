package com.example.dmaprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.dmaprojecttest2.Adapter.FragmentAdapter;
import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.google.android.gms.maps.MapFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(fragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
