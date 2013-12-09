package com.example.and_week2_listview1;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CursorAdapter extends ArrayAdapter<Book>{
	private ArrayList<Book> books;

	public CursorAdapter(Context context, int resource, ArrayList<Book> objects) {
		super(context, resource, objects);
		this.books = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(R.layout.book_row, null);
		}
		
		Book book = books.get(position);
		
		TextView textView1 = (TextView) view.findViewById(R.id.textView1);
		TextView textView2 = (TextView) view.findViewById(R.id.textView2);
		
		textView1.setText(book.getTitle());
		textView2.setText(book.getAuthor());
		
		return view;
	}
	
	

}
