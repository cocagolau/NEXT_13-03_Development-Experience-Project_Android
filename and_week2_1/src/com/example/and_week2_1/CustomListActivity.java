package com.example.and_week2_1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CustomListActivity extends Activity implements OnItemClickListener{
	
	private ArrayList<ListData> listDataArray = new ArrayList<ListData>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list);
		
		for (int i=1; i<23; i++) {
			ListData data = new ListData(i+"-첫 번째 줄", i+"두 번째 줄", i+".jpg");
			listDataArray.add(data);
		}
		ListView listView = (ListView)findViewById(R.id.custom_list_listView);
		CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
		listView.setAdapter(customAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Log.i("test", (position+1) + "번 선택 됨");
		Log.i("test", "리스트 내용 " + listDataArray.get(position).getTitle());
	}

}
