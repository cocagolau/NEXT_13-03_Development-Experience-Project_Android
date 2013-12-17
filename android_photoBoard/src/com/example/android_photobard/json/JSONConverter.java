package com.example.android_photobard.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONConverter {
	
	// singleton pattern
	private static JSONConverter JSON_CONVERTER = null;
	
	// JSON Node names
	private static final String TAG_ARTICLES = "photoBoards";
	private static final String TAG_ID = "id";
	private static final String TAG_SIZE = "size";

	// contacts JSONArray
	JSONArray articles = null;
	
	
	public static JSONConverter getInstance() {
		if (JSONConverter.JSON_CONVERTER == null) {
			JSONConverter.JSON_CONVERTER = new JSONConverter();
		}
		return JSONConverter.JSON_CONVERTER;
	}
	
	
	public void getJson(String url) {
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
		
		
		 
		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);
		 
		try {
		    // Getting Array of Contacts
		    articles = json.getJSONArray(TAG_ARTICLES);
		     
		    // looping through All Contacts
		    for(int i = 0; i < articles.length(); i++){
		        JSONObject articleObj = articles.getJSONObject(i);
		         
		        // Storing each json item in variable
		        String id = articleObj.getString(TAG_ID);
		        String commentSize = articleObj.getString(TAG_SIZE);
		         
		    }
		} catch (JSONException e) {
		    e.printStackTrace();
		}
	}
	
	
}
