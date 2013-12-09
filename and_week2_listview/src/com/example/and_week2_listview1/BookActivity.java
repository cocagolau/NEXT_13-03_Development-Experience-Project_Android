package com.example.and_week2_listview1;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends ListActivity {
	private SQLiteDatabase db;
	private CursorAdapter cursorAdapter;
	private ArrayList<Book> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		list = new ArrayList<Book>();
		
		cursorAdapter = new CursorAdapter(this, android.R.layout.simple_list_item_2, list);
		setListAdapter(cursorAdapter);
	}
	
	public void openDB(View v) {
		db = openOrCreateDatabase("book.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		Toast.makeText(this, "DB 열기", Toast.LENGTH_SHORT).show();
	}
	
	public void dropDB(View v) {
		deleteDatabase("book.db");
		Toast.makeText(this, "DB 삭제", Toast.LENGTH_SHORT).show();
	}
	
	public void createTable(View v) {
		db.execSQL("create table if not exists books("
				+ "id integer primary key autoincrement, "
				+ "title string not null, "
				+ "author string not null)");
		Toast.makeText(this, "Table 생성", Toast.LENGTH_SHORT).show();
	}
	
	public void dropTable(View v) {
		db.execSQL("drop table if exists books");
		Toast.makeText(this, "table 삭제", Toast.LENGTH_SHORT).show();
	}
	
	public void selectBooks(View v) {
		list.clear();
		Cursor cursor = db.rawQuery("select * from books", null);
		
		while (cursor.moveToNext()) {
			Book book = new Book(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
			list.add(book);
		}
		cursor.close();
		cursorAdapter.notifyDataSetChanged();
	}
	
	public void newBook(View v) {
		Intent i = new Intent(this, BookNewActivity.class);
		startActivity(i);
	}
	
	

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		TextView textView1 = (TextView) view.findViewById(R.id.textView1);
		TextView textView2 = (TextView) view.findViewById(R.id.textView2);
		
		String title = textView1.getText().toString();
		String author = textView2.getText().toString();
		
		Intent i = new Intent(this, BookShowActivity.class);
		i.putExtra("title", title);
		i.putExtra("author", author);
		startActivity(i);
		
	}

}
