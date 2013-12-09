package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BookNewActivity extends Activity {
	private SQLiteDatabase db;
	
	private EditText titleText;
	private EditText authorText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_new);
		
		titleText = (EditText) findViewById(R.id.titleText);
		authorText = (EditText) findViewById(R.id.authorText);
	}
	
	public void openDB(View v) {
		db = openOrCreateDatabase("book.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		Toast.makeText(this, "DB 열기", Toast.LENGTH_SHORT).show();
	}
	
	public void saveBook(View v) {
		String newTitle = titleText.getText().toString();
		String newAuthor = authorText.getText().toString();
		
		db.execSQL("insert into books(title, author) values('" + newTitle + "', '" + newAuthor + "')");
		Toast.makeText(this, "Insert Query", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void cancelBook(View v) {
		Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
		finish();
	}


}
