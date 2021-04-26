package com.example.dmaprojecttest2.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dmaprojecttest2.Fragments.MenuFragment;
import com.example.dmaprojecttest2.MainActivity;
import com.example.dmaprojecttest2.R;
import com.google.gson.JsonObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static java.security.AccessController.getContext;

public class DbReport extends AsyncTask<Void, Void, Void> {

    String userId;
    int dumpsterId;
    String dumpsterType;
    String url;

    //gets the parameters for url
    public DbReport(String userId, int dumpsterId, String dumpsterType) {
        this.userId = userId;
        this.dumpsterId = dumpsterId;
        this. dumpsterType = dumpsterType;
    }

    //adapted from https://blog.codavel.com/how-to-integrate-httpurlconnection
    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        url = "https://solskov-jensen.dk/API/update.api.php?User=" + userId + "&DumpsterID=" + dumpsterId + "&DumpsterType=" + dumpsterType;

        try {
            URL urlAddress = new URL(url);
            urlConnection = (HttpURLConnection) urlAddress.openConnection();

            int code = urlConnection.getResponseCode();
            if (code !=  200) {
                throw new IOException("Invalid response from server: " + code);
                //Add toast message for different response codes...
            }

            /*BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                Log.i("data", line);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /*@Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;

        try {
            JsonObject postData = new JsonObject();
            postData.addProperty("User", "morpheus");
            postData.addProperty("DumpsterID", 1);
            postData.addProperty("DumpsterType", "leader");

            URL url = new URL("https://solskov-jensen.dk/API/update.api.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"));
            writer.write(postData.toString());
            writer.flush();

            int code = urlConnection.getResponseCode();
            System.out.println(code);
            if (code !=  201) {
                throw new IOException("Invalid response from server: " + code);
            }

            *//*BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                Log.i("data", line);
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }*/
}