package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener{

	private final String[] values = new String[] {"Toast", "Remove", "Check", "Single Choice", "Multiple Choice", "OneLine Activated Single Choice", "TwoLine Activated Multiple Choice", "DB"};
	private ListView listView;
	private ArrayAdapter arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.listView);
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0 :
			intent = new Intent(getApplicationContext(), ToastListActivity.class);
			break;
		case 1 :
			intent = new Intent(getApplicationContext(), RemoveListActivity.class);
			break;
		case 2 :
			intent = new Intent(getApplicationContext(), CheckListActivity.class);
			break;
		case 3 :
			intent = new Intent(getApplicationContext(), SingleChoiceListActivity.class);
			break;
		case 4 :
			intent = new Intent(getApplicationContext(), MultipleChoiceListActivity.class);
			break;
		case 5 :
			intent = new Intent(getApplicationContext(), OneLineActivatedSingleChoice.class);
			break;
		case 6 :
			intent = new Intent(getApplicationContext(), TwoLineActivateMultipleChoiceActivity.class);
			break;
		case 7 :
			intent = new Intent(getApplicationContext(), BookActivity.class);
			break;
		case 8 :
			break;
		case 9 :
			intent = new Intent();
			intent.setAction("com.example.listadapter.dbook");
			break;
		}
		
		if (intent != null) {
			startActivity(intent);
		}
		
	}

}
