package com.example.dmaprojecttest2.db;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dmaprojecttest2.Fragments.MapsFragment;
import com.example.dmaprojecttest2.Fragments.MenuFragment;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.dmaprojecttest2.Fragments.MapsFragment.view;
import static java.security.AccessController.getContext;

public class DbFetchType extends AsyncTask<String,Void,List<String[]>> {
    //List<String[]> result = new ArrayList<>();
    String data;
    String dumpsterId;

    //gets the dumpsterId from the clicked marker
    public DbFetchType(String dumpsterId){
        this.dumpsterId = dumpsterId;
    }

    @Override
    protected List<String[]> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;

        List<String[]> result = new ArrayList<>();

        String urlAddress = "https://solskov-jensen.dk/API/fetchType.api.php?DumpsterID=" + dumpsterId + "&UserID=" + MainActivity.userId;

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
                String one = j.getString("Dumpster_Type");
                String two = j.getString("Dumpster_Full");
                String[] newRow = {one, two};
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
        MainActivity.fetchTypeResult = result;

        //Toast.makeText(view.getContext(), result.get(0)[0], Toast.LENGTH_SHORT).show();

        FragmentActivity activity = (FragmentActivity) view.getContext();
        FragmentManager manager = activity.getSupportFragmentManager();

        if (manager.findFragmentById(R.id.frameLayout_menu) != null) {
            MainActivity.destroyMenuFragment(view);
        }
        try {
            MainActivity.createMenuFragment(view);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
