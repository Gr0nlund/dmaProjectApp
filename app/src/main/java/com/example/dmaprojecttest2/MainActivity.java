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
        FragmentTransaction ft = manager.beginTransaction();
        ft.remove(mapsFragment);
        Fragment newInstance = recreateFragment(mapsFragment);
        ft.add(R.id.frameLayout_smallMaps, newInstance);
        ft.commit();
    }

    //adapted from https://stackoverflow.com/questions/9906254/illegalstateexception-cant-change-container-id-of-fragment
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
}
