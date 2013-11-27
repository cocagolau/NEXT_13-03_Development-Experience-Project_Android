package com.example.android_test;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button button1;
	private ImageButton imgButton;
	private TextView textView;
	private Button button2;
	private EditText editText;
	
	private ImageView imageView;
	private Button button3;
	private boolean isChanged = false;
	private String imgSrc = "";
	
	private int touchCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1 = (Button) findViewById (R.id.button1);
		button1.setOnClickListener(this);
		
		imgButton = (ImageButton) findViewById (R.id.imageButton1);
		imgButton.setOnClickListener(this);
		
		textView = (TextView) findViewById (R.id.textView1);
		textView.setOnClickListener(this);
		
		button2 = (Button) findViewById (R.id.button2);
		button2.setOnClickListener(this);
		
		editText = (EditText) findViewById (R.id.editText1);
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		imgSrc = "1.jpg";
		setImage (imageView, imgSrc);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1 :
			Toast.makeText(MainActivity.this, "clicked hi", Toast.LENGTH_SHORT).show();
			break;
		case R.id.imageButton1 :
			++touchCount;
			textView.setText(touchCount + " clicked");
			Toast.makeText(MainActivity.this, "imgButton Clicked", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.button2 :
			editText.setText("this is: " + textView.getText());
			break;
		
		case R.id.button3 :
			if (!isChanged) {
				isChanged = true;
				imgSrc = "2.jpg";
				
			} else {
				isChanged = false;
				imgSrc = "1.jpg";
			}
			setImage(imageView, imgSrc);
			break;	
		}
	}
	public void setImage (ImageView imageView, String imgSrc) {
		InputStream ims;
		try {
			ims = getAssets().open(imgSrc);
			Drawable d = Drawable.createFromStream(ims, null);
			imageView.setImageDrawable(d);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
