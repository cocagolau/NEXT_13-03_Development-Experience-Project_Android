package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class CheckListActivity extends ListActivity {

	private ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_check_list);
		
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, Data.titles);
		setListAdapter(arrayAdapter);
	}
	

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		
		CheckedTextView ctv = (CheckedTextView) view;
		ctv.setChecked(!ctv.isChecked());
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_list, menu);
		return true;
	}

}
