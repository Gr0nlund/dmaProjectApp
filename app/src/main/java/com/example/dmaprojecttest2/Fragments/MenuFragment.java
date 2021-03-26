package com.example.dmaprojecttest2.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class MenuFragment extends Fragment {

    String[] SortingTypes;
    int[] images = {R.drawable.restaffald,R.drawable.papir_pap,R.drawable.plast_metal,R.drawable.glas,R.drawable.minielektronik,R.drawable.stort_affald,R.drawable.elpaerer,R.drawable.farligt_affald,R.drawable.batterier};

    RecyclerView recyclerView;

    private static MenuFragment INSTANCE = null;
    View view;

    public MenuFragment(){

    }

    public static MenuFragment getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MenuFragment();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        recyclerView = recyclerView.findViewById(R.id.recyclerView);
        SortingTypes = getResources().getStringArray(R.array.SortingTypes);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_menu,container,false);
        return view;

    }
}
