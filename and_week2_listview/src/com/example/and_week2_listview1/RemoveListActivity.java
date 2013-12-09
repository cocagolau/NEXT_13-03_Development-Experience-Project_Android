package com.example.and_week2_listview1;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RemoveListActivity extends ListActivity {
	
	private ArrayList<String> list;
	private ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_remove_list);
		
		list = new ArrayList<String>();
		initializeList();
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
		setListAdapter(arrayAdapter);
	}
	
	

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		list.remove(position);
		if (list.isEmpty()) {
			initializeList();
		}
		arrayAdapter.notifyDataSetChanged();
	}



	private void initializeList() {
		for (String value : Data.titles)
			list.add(value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_list, menu);
		return true;
	}

}
