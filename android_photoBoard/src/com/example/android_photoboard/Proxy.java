package com.example.android_photoboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class Proxy {
	public String getJSON() {
		
		try {
			URL url = new URL (MainActivity.ACCESS_URL + "loadData.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Accept", "application/json");
			
			conn.setDoInput(true);
			conn.connect();
			
			
			int status = conn.getResponseCode();
			Log.i("Proxy.getJSON()", "ProxyResponseCode: " + status);
			
			switch(status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				
				while ( (line = br.readLine()) != null ) {
					sb.append(line + "\n");
				}
				br.close();
				
				return sb.toString();
			}
		}
		catch (Exception e) {
			Log.i("Proxy.getJSON()", "Network error: " + e.getMessage());
			
		}
		
		return null;
	}
}
