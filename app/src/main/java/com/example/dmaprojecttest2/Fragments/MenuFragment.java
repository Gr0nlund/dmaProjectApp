package com.example.dmaprojecttest2.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.dmaprojecttest2.Adapter.RecyclerViewAdapter;
import com.example.dmaprojecttest2.Adapter.RecyclerViewAdapter;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;

public class MenuFragment extends Fragment {

    String[] sortingTypes = getResources().getStringArray(R.array.SortingTypes);
    int[] images = {R.drawable.restaffald,R.drawable.papir_pap,R.drawable.plast_metal,R.drawable.glas,R.drawable.minielektronik,R.drawable.stort_affald,R.drawable.elpaerer,R.drawable.farligt_affald,R.drawable.batterier};

    RecyclerView recyclerView;

/*
    *//*private static MenuFragment INSTANCE;

    public MenuFragment(){

    }

    public static MenuFragment getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new MenuFragment();
        }
        return INSTANCE;
    }*//*

    *//*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_menu, container, false);
        // 1. get a reference to recyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context, sortingTypes, images);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }*//*

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        recyclerView = recyclerView.findViewById(R.id.recyclerView1);
        sortingTypes = getResources().getStringArray(R.array.SortingTypes);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context, sortingTypes, images);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_menu,container,false);
        recyclerView = view.findViewById(R.id.recyclerView1);
        context = view.getContext();
        return view;
    }


    */

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        context = this.getContext();
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView = recyclerView.findViewById(R.id.recyclerView1);
        sortingTypes = getResources().getStringArray(R.array.SortingTypes);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(sortingTypes, images);
        recyclerView.setAdapter(recyclerViewAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }


}
