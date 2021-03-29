package com.example.dmaprojecttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.dmaprojecttest2.Fragments.MapsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.frameLayout_bigMaps, new MapsFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

        /*FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft2.replace(R.id.frameLayout_menu, new TestFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft2.commit();*/

    }
}
