package com.example.dmaprojecttest2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class PackageController {

    String User,DumpsterID,DumpsterType;

    public PackageController(String User, String DumpsterID, String DumpsterType) {
        this.User = User;
        this.DumpsterID = DumpsterID;
        this.DumpsterType = DumpsterType;
    }

    public String PackData()
    {
        JSONObject jo=new JSONObject();
        StringBuffer packedData=new StringBuffer();

        try
        {
            jo.put("User",User);
            jo.put("DumpsterID",DumpsterID);
            jo.put("DumpsterType",DumpsterType);

            Boolean firstValue=true;

            Iterator it=jo.keys();

            do {
                String key=it.next().toString();
                String value=jo.get(key).toString();

                if(firstValue)
                {
                    firstValue=false;
                }else
                {
                    packedData.append("&");
                }

                packedData.append(URLEncoder.encode(key,"UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value,"UTF-8"));

            }while (it.hasNext());

            return packedData.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
