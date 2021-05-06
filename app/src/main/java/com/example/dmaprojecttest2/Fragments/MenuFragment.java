package com.example.dmaprojecttest2.Fragments;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dmaprojecttest2.Interface.ClickListener;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.example.dmaprojecttest2.Adapter.RecyclerViewAdapter;
import com.example.dmaprojecttest2.db.DbClear;
import com.example.dmaprojecttest2.db.DbReport;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {

    private List<String> types = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();

    String userId = MainActivity.userId;
    public static int dumpsterId;
    String dumpsterType;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        //back button
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
        });

        //recyclerView menu
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMenu);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        for(int i = 0;i < MainActivity.fetchTypeResult.size();i++){
            types.add(MainActivity.fetchTypeResult.get(i)[0]);
            String image = MainActivity.fetchTypeResult.get(i)[0];
            int imageId = 0;
            //switch handling image selection
            switch(image.toLowerCase()) {
                case "restaffald":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("restaffald_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("restaffald_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "pap & papir":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("pap_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("pap_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "plast & metal":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("plast_metal_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("plast_metal_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "glas":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("glas_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("glas_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "batterier":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("batterier_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("batterier_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "stort affald":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("stort_affald_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("stort_affald_red", "drawable", getContext().getPackageName());
                    }
                    break;
                case "minielektronik":
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("minielektronik_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("minielektronik_red", "drawable", getContext().getPackageName());
                    }
                    break;
                default:
                    if(Integer.parseInt(MainActivity.fetchTypeResult.get(i)[1]) == 0){
                        imageId = getContext().getResources().getIdentifier("questionmark_green", "drawable", getContext().getPackageName());
                    } else {
                        imageId = getContext().getResources().getIdentifier("questionmark_red", "drawable", getContext().getPackageName());
                    }
            }
            images.add(imageId);

        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(types, images, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                if(Integer.parseInt(MainActivity.fetchTypeResult.get(position)[1]) == 0){
                    dumpsterType = types.get(position);
                    new DbReport(userId,dumpsterId,dumpsterType).execute();
                    Toast.makeText(getContext(), "Report sent for " + dumpsterType, Toast.LENGTH_SHORT).show();
                } else {
                    dumpsterType = types.get(position);
                    new DbClear(userId,dumpsterId,dumpsterType).execute();
                    Toast.makeText(getContext(), "Clear sent for " + dumpsterType, Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}