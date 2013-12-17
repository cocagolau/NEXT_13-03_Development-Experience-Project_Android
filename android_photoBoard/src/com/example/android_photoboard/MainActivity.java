package com.example.android_photoboard;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener, OnClickListener{

	private Button writingBtn;	
	private ArrayList<Article> articleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// dao
		Dao dao = new Dao(getApplicationContext());
		String textJsonData = dao.getJsonTestData();
		dao.insertJsonData(textJsonData);
		articleList = dao.getArticleList();
		
		
		writingBtn = (Button)findViewById(R.id.writing_btn);
		writingBtn.setOnClickListener(this);
		
		
		ListView listView = (ListView)findViewById(R.id.photo_listView);
		ArticleAdapter articleAdapter = new ArticleAdapter(this, R.layout.photo_list_row, articleList);
		listView.setAdapter(articleAdapter);
		listView.setOnItemClickListener(this);
	}


	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()) {
		case R.id.writing_btn : 
			Intent wrtingActivityIntent = new Intent(this, WritingActivity.class); // context, 이동할 class
			startActivity(wrtingActivityIntent);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Intent contentsActivityIntent = new Intent(this, ContentsActivity.class);
		contentsActivityIntent.putExtra("articleNumber", articleList.get(position).getArticleNumber());
		startActivity(contentsActivityIntent);
	}

}
