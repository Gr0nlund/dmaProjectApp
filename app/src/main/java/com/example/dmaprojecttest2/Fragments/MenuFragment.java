package com.example.dmaprojecttest2.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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

import com.example.dmaprojecttest2.Interface.AsyncResponse;
import com.example.dmaprojecttest2.Interface.ClickListener;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.example.dmaprojecttest2.Adapter.RecyclerViewAdapter;
import com.example.dmaprojecttest2.db.HTTPfetchType;
import com.example.dmaprojecttest2.db.HTTPreport;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;



public class MenuFragment extends Fragment {

    int[] images = {R.drawable.restaffald,R.drawable.papir_pap,R.drawable.plast_metal,R.drawable.glas,R.drawable.minielektronik,R.drawable.stort_affald,R.drawable.elpaerer,R.drawable.farligt_affald,R.drawable.batterier};
    private final String[] types = {"Restaffald","Papir & pap","Plast & metal","Glas","Minielektronik","Stort affald","Elpærer","Farligt affald","Batterier"};

    String userId = MainActivity.userId;
    int dumpsterId;
    String dumpsterType;

    public static MenuFragment newInstance() {
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
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMenu);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(types, images, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                dumpsterType = "Rest";
                dumpsterId = 5;
                new HTTPreport(userId,dumpsterId,dumpsterType).execute();
                Toast.makeText(getContext(), "REPORT SENT  " + position, Toast.LENGTH_SHORT).show();
            }

        });

        recyclerView.setAdapter(adapter);

        return view;
    }
}
