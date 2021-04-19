package com.example.dmaprojecttest2.db;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dmaprojecttest2.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPfetchType extends AsyncTask<String,Void,String> {
    String result;
    String dumpsterId;

    public HTTPfetchType(String dumpsterId){
        this.dumpsterId = dumpsterId;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;

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
                result = line;
                Log.i("data", line);
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
    public void onPostExecute(String result) {
        MainActivity.fetchTypeResult = result;
    }
}
