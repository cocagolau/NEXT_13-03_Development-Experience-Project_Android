package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ToastListActivity extends ListActivity{

	private ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_toast_list); 중요 
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.titles);
		setListAdapter(arrayAdapter);
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		String item = (String)listView.getItemAtPosition(position);
		Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toast_list, menu);
		return true;
	}

}
