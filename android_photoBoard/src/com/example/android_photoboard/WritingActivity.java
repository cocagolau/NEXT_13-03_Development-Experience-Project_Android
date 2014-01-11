package com.example.android_photoboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class WritingActivity extends Activity implements OnClickListener{
	
	private static final int REQUEST_PHOTO_ALBUM = 1;
	
	public CookieManager cookieManager;
	
	private String filePath;
	private String fileName;
	private ProgressDialog progressDialog;
	
	private EditText etAuthor;
	private EditText etTitle;
	
	private ImageButton ibPhoto;
	private Button buUpload;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writing);
		
		try {
			CookieSyncManager.createInstance(getApplicationContext());
			cookieManager = CookieManager.getInstance();
			String cookie = cookieManager.getCookie(MainActivity.ACCESS_URL);
			
			
			etAuthor = (EditText)findViewById(R.id.writing_writerName_text);
			etTitle = (EditText)findViewById(R.id.writing_title_text);
			
			ibPhoto = (ImageButton)findViewById(R.id.writing_imageBtn_btn);
			ibPhoto.setOnClickListener(this);
			
			buUpload = (Button)findViewById(R.id.writing_writeBtn);
			buUpload.setOnClickListener(this);
		}
		catch (Exception e) {
			Log.e("WritingActivity.onCreate()", "onCreate Error: " + e);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			getMenuInflater().inflate(R.menu.writing, menu);
			return true;
		}
		catch (Exception e) {
			Log.e("WritingActivity.onCreateOptionsMenu()", "onCreateOptionsMenu Error: " + e);
		}
		return false;
	}

	@Override
	public void onClick(View view) {
		
		try {
			switch (view.getId()) {
			case R.id.writing_imageBtn_btn :
				Intent intent = new Intent(Intent.ACTION_PICK);
				
				intent.setType(Images.Media.CONTENT_TYPE);
				intent.setData(Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, REQUEST_PHOTO_ALBUM);
				break;
			case R.id.writing_writeBtn :
				Log.e("WritingActivity", ".onClick() - writeBtn clicked");
				final Handler HANDER = new Handler(); 
				new Thread() {
					public void run() {
						
						HANDER.post(new Runnable() {
							public void run() {
								progressDialog = ProgressDialog.show(WritingActivity.this, "", "uploading");
							}
						});
//						String ID = Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//						String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA).format(new Date());
						Article article = new Article(
								0,
								etTitle.getText().toString(),
								etAuthor.getText().toString(),
								fileName);
						ProxyUP proxyUP = new ProxyUP(cookieManager);
						proxyUP.uploadArticle(article, filePath);
						
						HANDER.post(new Runnable() {
							public void run() {
								progressDialog.cancel();
								finish();
							}
						});
					}
				}.start();
				break;
			}
		}
		catch (Exception e) {
			Log.e("WritingActivity", "onClick() - Error: " + e);
		}
	}
	
	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		try {
			if (requestCode == REQUEST_PHOTO_ALBUM) {
				Uri uri = getRealPathUri(data.getData());
				filePath = uri.toString();
				fileName = uri.getLastPathSegment();
				Bitmap bitmap = BitmapFactory.decodeFile(filePath);
				ibPhoto.setImageBitmap(bitmap);
			}
		}
		catch(Exception e) {
			Log.e("WritingActivity", "onActivityResult() - error: " + e);
		}
	}
	
	private Uri getRealPathUri(Uri uri) {
		Uri filePathUri = uri;
		
		try {
			if (uri.getScheme().toString().compareTo("content") == 0) {
				Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
				if (cursor.moveToFirst()) {
					int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					filePathUri = Uri.parse(cursor.getString(column_index));
				}
			}
		}
		catch (Exception e) {
			Log.e("WritingActivity", "getRealPathUri() - Error: " + e);
		}
		return filePathUri;
	}

}
