package com.example.dmaprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    static MapsFragment mapsFragment = new MapsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Fragment newInstance = recreateFragment(mapsFragment);
            ft.add(R.id.frameLayout_smallMaps, newInstance);
            ft.commit();
        }
    }

    //adapted from https://stackoverflow.com/questions/9906254/illegalstateexception-cant-change-container-id-of-fragment
    //used to recreate mapsFragment when moving it from one frameLayout to another
    private static Fragment recreateFragment(Fragment f){
        try {
            FragmentActivity activity = (FragmentActivity) f.getContext();
            FragmentManager fManager = activity.getSupportFragmentManager();
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
