package com.example.and_week2_listview1;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TwoLineActivateMultipleChoiceActivity extends ListActivity {

	private SimpleAdapter simpleAdapter;
	private ArrayList<HashMap<String, String>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_two_line_activate_multiple_choice);
		
		list = new ArrayList<HashMap<String, String>>(2);
		initializeList();
		
		String[] from = new String[] {"title", "subTitle"};
		int[] to = new int[] {android.R.id.text1, android.R.id.text2};
		
		simpleAdapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_activated_2, from, to);
		setListAdapter(simpleAdapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	private void initializeList() {
		for (int i=0; i<Data.titles.length; i++) {
			HashMap<String, String> innerData = new HashMap<String, String>();
			innerData.put("title", Data.titles[i]);
			innerData.put("subTitle", Data.subTitles[i]);
			list.add(innerData);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.two_line_activate_multiple_choice,
				menu);
		return true;
	}

}
