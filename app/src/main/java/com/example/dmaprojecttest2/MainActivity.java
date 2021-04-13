package com.example.dmaprojecttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;

import java.util.Objects;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class MainActivity extends AppCompatActivity {

    private static MapsFragment mapsFragment = new MapsFragment();
    private static String userId;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_bigMaps, mapsFragment)
                .commitNow();
    }

    public static void createMenuFragment(View view) {
        //creates the menu and places it in frameLayout
        //creating the menuFragment should take parameters for the specific site
        FragmentActivity activity = (FragmentActivity)view.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft2 = manager.beginTransaction();
        // Replace the contents of the container with the new fragment
        ft2.replace(R.id.frameLayout_menu, MenuFragment.newInstance());
        //completes transaction
        ft2.commit();

        //moves mapsFragment from one frameLayout to another when it opens the menu
        //but only if it is not already in frameLayout_smallMaps
        if(manager.findFragmentById(R.id.frameLayout_smallMaps) == null){
            FragmentTransaction ft = manager.beginTransaction();
            ft.remove(mapsFragment);
            mapsFragment = (MapsFragment) recreateFragment(mapsFragment);
            ft.add(R.id.frameLayout_smallMaps, mapsFragment);
            ft.commit();
        }
    }

    public static void backToMaps(View view){
        FragmentActivity activity = (FragmentActivity)view.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();

        FragmentTransaction ft4 = manager.beginTransaction();
        ft4.remove(mapsFragment);
        mapsFragment = (MapsFragment) recreateFragment(mapsFragment);
        ft4.add(R.id.frameLayout_bigMaps, mapsFragment);
        ft4.commit();
    }

    //adapted from https://stackoverflow.com/questions/9906254/illegalstateexception-cant-change-container-id-of-fragment
    //used to recreate mapsFragment when moving it from one frameLayout to another
    private static Fragment recreateFragment(Fragment f){
        try {
            //FragmentActivity activity = (FragmentActivity) f.getContext();
            //FragmentManager fManager = activity.getSupportFragmentManager();
            FragmentManager fManager = f.getActivity().getSupportFragmentManager();
            Fragment.SavedState savedState = fManager.saveFragmentInstanceState(f);

            Fragment newInstance = f.getClass().newInstance();
            newInstance.setInitialSavedState(savedState);

            return newInstance;
        }
        catch (Exception e) // InstantiationException, IllegalAccessException
        {
            throw new RuntimeException("Cannot reinstantiate fragment " + f.getClass().getName(), e);
        }
    }

    //destroys the menuFragment when it is closed or a new one is opened
    public static void destroyMenuFragment(View view){
        FragmentActivity activity = (FragmentActivity)view.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction ft3 = manager.beginTransaction();

        ft3.remove(Objects.requireNonNull(manager.findFragmentById(R.id.frameLayout_menu)));
        ft3.commit();
    }
}
