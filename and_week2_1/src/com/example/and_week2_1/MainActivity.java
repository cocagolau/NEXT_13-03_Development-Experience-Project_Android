package com.example.and_week2_1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button mButtonSimpleList1;
	private Button mButtonSimpleList2;
	private Button mButtonCustomList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButtonSimpleList1 = (Button)findViewById(R.id.mainBtn_simpleList1);
		mButtonSimpleList2 = (Button)findViewById(R.id.mainBtn_simpleList2);
		mButtonCustomList = (Button)findViewById(R.id.mainBtn_customList);
		
		mButtonSimpleList1.setOnClickListener(this);
		mButtonSimpleList2.setOnClickListener(this);
		mButtonCustomList.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()) {
		case R.id.mainBtn_simpleList1 :
			Intent intentSimpleList1 = new Intent(this, SimpleList1Activity.class);
			startActivity(intentSimpleList1);
			break;
		case R.id.mainBtn_simpleList2 :
			Intent intentSimpleList2 = new Intent(this, SimpleList2Activity.class);
			startActivity(intentSimpleList2);
			break;
		case R.id.mainBtn_customList :
			Intent intentCustomList = new Intent(this, CustomListActivity.class);
			startActivity(intentCustomList);
			break;
		}
		
	}

}
