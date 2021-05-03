package com.example.dmaprojecttest2.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.dmaprojecttest2.Fragments.MapsFragment.view;

public class DbFetchAll extends AsyncTask<String,Void, List<String[]>> {
    String data;

    @Override
    protected List<String[]> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;

        List<String[]> result = new ArrayList<>();

        String urlAddress = "https://solskov-jensen.dk/API/fetchAll.api.php?UserID=" + MainActivity.userId;

        try {
            URL url = new URL(urlAddress);
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();
            if (code !=  200) {
                throw new IOException("Invalid response from server: " + code);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                data = line;
                Log.i("data", line);
            }
            JSONArray json = new JSONArray(data);

            for(int i = 0; i < json.length(); i++){
                JSONObject j = json.getJSONObject(i);
                String id = j.getString("Dumpster_ID");
                String lng = j.getString("Dumpster_Longtitude");
                String lat = j.getString("Dumpster_Latitude");
                String full = j.getString("Dumpster_Full");
                String[] newRow = {id,lng,lat,full};
                result.add(newRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return result;
    }

    @Override
    public void onPostExecute(List<String[]> result) {
        //create all markers from db here
        List<double[]> arr = new ArrayList<>();
        int maxId = 0;

        //finds the max id value
        for (int i = 0; i < result.size(); i++) {
            if (Integer.parseInt(result.get(i)[0]) > maxId) {
                maxId = Integer.parseInt(result.get(i)[0]);
            }
        }

        //checks amount of full types at a site and creates a new List<Double[]> to make marker creation easier
        for(int id = 1; id < maxId+1; id++){
            int fullAmount = 0;
            int totalTypeAmount = 0;
            double lat = 0;
            double lng = 0;
            for(int i = 0; i < result.size(); i++){
                if(Integer.parseInt(result.get(i)[0]) == id && Integer.parseInt(result.get(i)[3]) == 1){
                    fullAmount++;
                }
                if(Integer.parseInt(result.get(i)[0]) == id){
                    totalTypeAmount++;
                    lat = Double.parseDouble(result.get(i)[1]);
                    lng = Double.parseDouble(result.get(i)[2]);
                }
            }
            //only adds a dumpster once with amount of fullness/color
            if(fullAmount == 0){
                double[] row = {id,lat,lng,0};
                arr.add(row);
            } else if(fullAmount > 0 && fullAmount < totalTypeAmount){
                double[] row = {id,lat,lng,1};
                arr.add(row);
            } else if(fullAmount == totalTypeAmount){
                double[] row = {id,lat,lng,2};
                arr.add(row);
            }

            //Toast.makeText(MapsFragment.view.getContext(), String.valueOf(id) + " - " + Double.parseDouble(result.get(id)[1]), Toast.LENGTH_SHORT).show();
        }

        for(int i = 0; i < arr.size(); i++) {
            LatLng latLng = new LatLng(arr.get(i)[1], arr.get(i)[2]);
            BitmapDescriptor b;
            //Toast.makeText(MapsFragment.view.getContext(), String.valueOf(arr.get(i)[1]), Toast.LENGTH_SHORT).show();

            //finds the correct color for marker icon
            /*if ((int) arr.get(i)[3] == 0) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.ic_dumpster_green);
            } else if ((int) arr.get(i)[3] == 1) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.ic_dumpster_yellow);
            } else if ((int) arr.get(i)[3] == 2) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.ic_dumpster_red);
            } else {
                //defaults dumpster to black
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.ic_dumpster);
            }*/

            if ((int) arr.get(i)[3] == 0) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.trash_green);
            } else if ((int) arr.get(i)[3] == 1) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.trash_yellow);
            } else if ((int) arr.get(i)[3] == 2) {
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.trash_red);
            } else {
                //defaults dumpster to black
                b = MapsFragment.bitmapDescriptorFromVector(MapsFragment.view.getContext(), R.drawable.trash);
            }

            //adds marker to map
            MapsFragment.map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(b)
                    .title(String.valueOf((int) arr.get(i)[0]))
                    .anchor(0.5f, 0.5f));
        }
    }
}
