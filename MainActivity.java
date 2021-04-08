package com.example.dmaprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static MapsFragment mapsFragment = new MapsFragment();
    String URL= "https://solskov-jensen.dk/API/update.api.php";
    //Dummy værdier
    EditText UserTxt,DumpsterIDTxt,DumpsterTypeTxt;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout_bigMaps, mapsFragment)
                .commitNow();
        //Dummy
        UserTxt= (EditText) findViewById(R.id.nameEditTxt);
        DumpsterIDTxt= (EditText) findViewById(R.id.posEditTxt);
        DumpsterTypeTxt= (EditText) findViewById(R.id.teamEditTxt);
        saveBtn= (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //START ASYNC TASK
                PackageSender s=new PackageSender(MainActivity.this,URL,UserTxt,DumpsterIDTxt,DumpsterTypeTxt);
                s.execute();
            }
        });
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
