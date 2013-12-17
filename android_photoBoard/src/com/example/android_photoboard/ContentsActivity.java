package com.example.android_photoboard;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentsActivity extends Activity {
	
	// url to make request
	private String url = "http://cocagola.cafe24.com/next/3rd/devExperience/dec7board.json";
	
	TextView writer;
	TextView writeDate;
	TextView title;
	TextView content;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contents);
		
		int articleNumber = getIntent().getExtras().getInt("articleNumber");

		writer = (TextView) findViewById(R.id.contents_writerName_textView);
		writeDate = (TextView) findViewById(R.id.contents_writeDate_textView);
		image = (ImageView) findViewById(R.id.contents_image_imageView);
		title = (TextView) findViewById(R.id.contents_title_textView);
		content = (TextView) findViewById(R.id.contents_content_textView);
		
		
		Dao dao = new Dao (getApplicationContext());
		Article article = dao.getArticleByArticleNumber(articleNumber);
		
		writer.setText(article.getWriter());
		writeDate.setText(article.getWriteDate());
		title.setText(article.getTitle());
		content.setText(article.getContent());
		
		try {
			InputStream ins = getApplicationContext().getAssets().open("articlePhoto/" + article.getImgName());
			Drawable draw = Drawable.createFromStream(ins, null);
			image.setImageDrawable(draw);
		}
		catch (IOException ioe) {
			Log.e("ContentsActivity.onCreate", ioe.getMessage());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contents, menu);
		return true;
	}

}
