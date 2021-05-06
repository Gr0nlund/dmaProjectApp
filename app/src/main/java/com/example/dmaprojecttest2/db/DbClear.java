package com.example.dmaprojecttest2.db;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DbClear extends AsyncTask<Void, Void, Void> {

    String userId;
    int dumpsterId;
    String dumpsterType;
    String url;

    //gets the parameters for url
    public DbClear(String userId, int dumpsterId, String dumpsterType) {
        this.userId = userId;
        this.dumpsterId = dumpsterId;
        this. dumpsterType = dumpsterType;
    }

    //adapted from https://blog.codavel.com/how-to-integrate-httpurlconnection
    @Override
    protected Void doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        url = "https://solskov-jensen.dk/API/clear.api.php?UserID=" + userId + "&DumpsterID=" + dumpsterId + "&DumpsterType=" + dumpsterType;

        try {
            URL urlAddress = new URL(url);
            urlConnection = (HttpURLConnection) urlAddress.openConnection();

            int code = urlConnection.getResponseCode();
            if (code !=  200) {
                throw new IOException("Invalid response from server: " + code);
                //Could add toast messages for different response codes...
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}