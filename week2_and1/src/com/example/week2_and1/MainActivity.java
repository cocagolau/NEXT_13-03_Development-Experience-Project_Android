package com.example.week2_and1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button simpleList1;
	private Button simpleList2;
	private Button customList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		simpleList1 = (Button)findViewById(R.id.SimpleListView1);
		simpleList2 = (Button)findViewById(R.id.SimpleListView2);
		customList = (Button)findViewById(R.id.CustomListView);
		
		simpleList1.setOnClickListener(this);
		simpleList2.setOnClickListener(this);
		customList.setOnClickListener(this);

	}
	
	public void onClick(View arg0) {
			switch(arg0.getId()) {
			case R.id.SimpleListView1 :
//				Intent simpleList1Intent = new Intent (this, SimpleList1.class);
//				startActivity(simpleList1Intent);
				System.out.print("1");
				break;
				
			case R.id.SimpleListView2 :
//				Intent simpleList2Intent = new Intent (this, SimpleList2.class);
//				startActivity(simpleList2Intent);
				System.out.print("2");
				break;
				
			case R.id.CustomListView :
//				Intent customListIntent = new Intent (this, CustomList.class);
//				startActivity(customListIntent);
				System.out.print("3");
				break;
			}
	}
}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
