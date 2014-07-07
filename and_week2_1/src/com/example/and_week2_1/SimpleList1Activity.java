package com.example.and_week2_1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SimpleList1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_list1);
		
		ListView listView = (ListView)findViewById(R.id.simple_list1_listView);
		List<String> simpleList1Array = new ArrayList<String>();
		simpleList1Array.add("서동규");
		simpleList1Array.add("양현석");
		simpleList1Array.add("임요한");
		simpleList1Array.add("박세훈");
		simpleList1Array.add("뽀삐");
		simpleList1Array.add("갑:이진우");
		
		ArrayAdapter<String> simpleList1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, simpleList1Array);
		listView.setAdapter(simpleList1Adapter);
	}

}
