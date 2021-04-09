package com.example.dmaprojecttest2.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.example.dmaprojecttest2.Adapter.RecyclerViewAdapter;


public class MenuFragment extends Fragment {

    int[] images = {R.drawable.restaffald,R.drawable.papir_pap,R.drawable.plast_metal,R.drawable.glas,R.drawable.minielektronik,R.drawable.stort_affald,R.drawable.elpaerer,R.drawable.farligt_affald,R.drawable.batterier};
    private String[] types = {"Restaffald","Papir & pap","Plast & metal","Glas","Minielektronik","Stort affald","Elpærer","Farligt affald","Batterier"};

    public static MenuFragment newInstance(){
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)view.getContext();
                FragmentManager manager = activity.getSupportFragmentManager();

                if (manager.findFragmentById(R.id.frameLayout_menu) != null) {
                    MainActivity.destroyMenuFragment(view);
                }
                MainActivity.backToMaps(view);
            }
                //MainActivity.destroyMenuFragment(view);
                //MainActivity.backToMaps(view);
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMenu);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(types, images);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
