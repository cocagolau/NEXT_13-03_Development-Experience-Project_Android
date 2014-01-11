package com.example.android_photoboard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.example.android_photoboard.ado.Dao;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentsActivity extends Activity {
	
	
	TextView author;
//	TextView writeDate;
	TextView title;
//	TextView content;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents);
		
		
		try {
			int id = getIntent().getExtras().getInt("id");
			Log.e("ContentsActicity", ".onCreate() - id: " + id);
	
			author = (TextView) findViewById(R.id.contents_writerName_textView);
//			writeDate = (TextView) findViewById(R.id.contents_writeDate_textView);
			image = (ImageView) findViewById(R.id.contents_image_imageView);
			title = (TextView) findViewById(R.id.contents_title_textView);
//			content = (TextView) findViewById(R.id.contents_content_textView);
			
			
			Dao dao = new Dao (getApplicationContext());
			Article article = dao.getArticleByArticleNumber(id);
			
			author.setText(article.getAuthor());
//			writeDate.setText(article.getWriteDate());
			title.setText(article.getTitle());
//			content.setText(article.getContent());
			
			String img_path = getApplicationContext().getFilesDir().getPath() + "/" + article.getImgName();
			File img_load_path = new File(img_path);
			
			if (img_load_path.exists()) {
				Bitmap bitmap = BitmapFactory.decodeFile(img_path);
				image.setImageBitmap(bitmap);
			}
		}
		catch (Exception e) {
			Log.e("ContentsActivity.onCreate()", "onCreate Error: " + e);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			getMenuInflater().inflate(R.menu.contents, menu);
			return true;
		}
		catch (Exception e) {
			Log.e("Contents.Activity.opCreateOptionsMenu()", "onCreateOptionsMenu Error: " + e);
		}
		return false;
	}

}
