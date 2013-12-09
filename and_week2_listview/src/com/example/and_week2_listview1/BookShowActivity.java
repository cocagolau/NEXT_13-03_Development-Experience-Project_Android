package com.example.and_week2_listview1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BookShowActivity extends Activity {
	private SQLiteDatabase db;
	
	private EditText titleText;
	private EditText authorText;
	
	String oldTitle;
	String oldAuthor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_show);
		
		titleText = (EditText) findViewById(R.id.titleText);
		authorText = (EditText) findViewById(R.id.authorText);
		
		Intent i = getIntent();
		if (i != null) {
			oldTitle = i.getStringExtra("title");
			oldAuthor = i.getStringExtra("author");
			
			titleText.setText(oldTitle);
			authorText.setText(oldAuthor);
		}
	}
	
	public void openDB(View v) {
		db = openOrCreateDatabase("book.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		Toast.makeText(this, "DB 열기", Toast.LENGTH_SHORT).show();
	}
	
	public void updateBook(View v) {
		String newTitle = titleText.getText().toString();
		String newAuthor = authorText.getText().toString();
		db.execSQL("update books set title='" + newTitle
				+ "', author='" + newAuthor
				+ "', where title=" + oldTitle
				+ "', and author =" + oldAuthor + "'");
		Toast.makeText(this, "Update Query", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void deleteBook(View v) {
		String title = titleText.getText().toString();
		String author = authorText.getText().toString();
		db.execSQL("delete from books where title='" + title
				+ "' and author='" + author + "'");
		Toast.makeText(this, "Delete Query", Toast.LENGTH_SHORT).show();
		finish();
	}


}
