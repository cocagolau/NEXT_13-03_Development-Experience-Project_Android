package com.example.android_photoboard;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticleAdapter extends ArrayAdapter<Article>{
	
	private Context context;
	private int layoutResourseId;
	private ArrayList<Article> articleList;
	
	ArticleAdapter (Context context, int layoutResourseId, ArrayList<Article> articleList) {
		super(context, layoutResourseId, articleList);
		this.context = context;
		this.layoutResourseId = layoutResourseId;
		this.articleList = articleList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = null;
		
		try {
			row = convertView;
			if (row == null) {
				LayoutInflater inflater = ((Activity)context).getLayoutInflater();
				row = inflater.inflate(layoutResourseId, parent, false);
			}
			
			TextView title = (TextView) row.findViewById(R.id.photo_list_row_title_text_view);
			TextView writer = (TextView) row.findViewById(R.id.photo_list_row_subtitle_text_view);
			
			title.setText(articleList.get(position).getTitle());
			writer.setText(articleList.get(position).getWriter());
			
			ImageView imageView = (ImageView) row.findViewById(R.id.photo_list_row_image_view);
			String img_path = context.getFilesDir().getPath() + "/" + articleList.get(position).getImgName();
			File img_load_path = new File(img_path);
			
			if (img_load_path.exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(img_path);
				imageView.setImageBitmap(bitmap);
			}
		}
		catch (Exception e) {
			Log.e("ArticleAdapter.getView()", "getView Error: " + e);
		}
		
		return row;
	}
	
}
