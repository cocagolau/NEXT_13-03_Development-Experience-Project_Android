package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MultipleChoiceListActivity extends ListActivity {

	private ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_multiple_choice_list);
		
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, Data.titles);
		setListAdapter(arrayAdapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}


}
