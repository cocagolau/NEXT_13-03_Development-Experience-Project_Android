package com.example.and_week2_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleList2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_list2);

		ListView listView = (ListView)findViewById(R.id.simple_list2_listView);
		ArrayList<HashMap<String, String>> simpleList2Array = new ArrayList<HashMap<String, String>>(2);
		
		for (int i=0; i<10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("line1", "첫번째 줄의 " + i + "번");
			map.put("line2", "두번째 줄의 " + i + "번");
			simpleList2Array.add(map);
		}
		
		String[] from = {"line1", "line2"};
		int[] to = {android.R.id.text1, android.R.id.text2};
		SimpleAdapter simpleList2Adapter = new SimpleAdapter(this, simpleList2Array, android.R.layout.simple_list_item_2, from, to);
		
		listView.setAdapter(simpleList2Adapter);
	}
	
}
