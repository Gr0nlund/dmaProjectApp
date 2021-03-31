package com.example.dmaprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;
import com.example.dmaprojecttest2.Fragments.TestFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFragment();



        /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.add(R.id.frameLayout_bigMaps, new MenuFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();*/
    }


    public void createFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.frameLayout_bigMaps, new MenuFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }

    public static void createMenuFragment(View view) {
        FragmentActivity activity = (FragmentActivity)view.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft2 = manager.beginTransaction();
// Replace the contents of the container with the new fragment
        ft2.replace(R.id.frameLayout_menu, new TestFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft2.commit();

        FragmentTransaction ft = manager.beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.frameLayout_smallMaps, new MapsFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }


}
