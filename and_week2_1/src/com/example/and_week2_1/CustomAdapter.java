package com.example.and_week2_1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<ListData>{
	
	private Context context;
	private int layoutResourseId;
	private ArrayList<ListData> listData;
	
	
	public CustomAdapter (Context context, int layoutResourseId, ArrayList<ListData> listData) {
		super(context, layoutResourseId, listData);
		this.context = context;
		this.layoutResourseId = layoutResourseId;
		this.listData = listData;
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourseId, parent, false);
		}
		
		TextView tvText1 = (TextView) row.findViewById(R.id.custom_row_textView1);
		TextView tvText2 = (TextView) row.findViewById(R.id.custom_row_textView2);
	
		tvText1.setText(listData.get(position).getTitle());
		tvText2.setText(listData.get(position).getText());
		
		ImageView imgView = (ImageView) row.findViewById(R.id.custom_row_imageView);
		
		try {
			InputStream is = context.getAssets().open("sam/"+listData.get(position).getImage());
			Drawable d = Drawable.createFromStream(is, null);
			imgView.setImageDrawable(d);
			
		}
		catch (IOException e) {
			Log.e("customAdapter", e.getMessage());
		}
		
		
		return row;
		
	}

}
