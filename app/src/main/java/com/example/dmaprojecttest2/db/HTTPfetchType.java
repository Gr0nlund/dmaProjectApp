package com.example.dmaprojecttest2.db;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.dmaprojecttest2.MainActivity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HTTPfetchType extends AsyncTask<Void, Void, Void> {

    HTTPfetchType[] types;

    @Override
    protected Void doInBackground(Void... params) {
        final HttpURLConnection[] urlConnection = {null};

        //create new thread for the request to run on
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    try {
                        URL url = new URL("https://solskov-jensen.dk/API/fetchType.php?DumpsterID=1");
                        urlConnection[0] = (HttpURLConnection) url.openConnection();

                        int code = urlConnection[0].getResponseCode();
                        if (code != 200) {
                            throw new IOException("Invalid response from server: " + code);
                        }

                        BufferedReader rd = new BufferedReader(new InputStreamReader(
                                urlConnection[0].getInputStream()));
                        String line;
                        while ((line = rd.readLine()) != null) {
                            //JSONArray jsonArray = new JSONArray(line);
                            //JSONArray jsonarray = (JSONArray)jsonObject;
                            final ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                            objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

                            types = objectMapper.readValue(line, HTTPfetchType[].class);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (urlConnection[0] != null) {
                            urlConnection[0].disconnect();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            });
        thread.start();

        return null;
    }

    public String getData(){
        if(types != null){
            String type = types[0].toString();
            return type;
        }
        else{
            String type = "null";
            return type;
        }
    }

}
