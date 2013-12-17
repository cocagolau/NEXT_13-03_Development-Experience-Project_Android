package com.example.android_photobard.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;


// JSONParser class는 http요청에서 JSON data를 얻어와 JSONObject를 반환한다.
public class JSONParser {
 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
 
    // url을 인자로 받고 JSONObject을 반환 
    public JSONObject getJSONFromUrl(String url) {
 
        // Making HTTP request
        try {
            // defaultHttpClient
        	// post로 받아온 데이터를 InputStream is에 담는다. 
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
        	// string 변환
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        // string을 json object로 변환 
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
}