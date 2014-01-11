package com.example.android_photoboard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import com.example.android_photoboard.ado.Dao;



public class MainActivity extends Activity implements OnItemClickListener, OnClickListener, ISideNavigationCallback{

	public static final String ACCESS_URL = "http://10.73.43.37:8080/";
	
	public CookieManager cookieManager;
	
	private SideNavigationView sideNavigationView;
	
	private Button writingBtn;
	private Button refreshBtn;
	private ListView listView;
	private ArrayList<Article> articleList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
			sideNavigationView.setMenuItems(R.menu.side_menu);
			sideNavigationView.setMenuClickCallback(this);
			sideNavigationView.setMode(Mode.LEFT);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			
			
			writingBtn = (Button)findViewById(R.id.main_writing_btn);
			refreshBtn = (Button)findViewById(R.id.main_refresh_btn);
			
			writingBtn.setOnClickListener(this);
			refreshBtn.setOnClickListener(this);
			
			listView = (ListView)findViewById(R.id.photo_listView);
		}
		catch (Exception e) {
			Log.e("MainActivity", "onCreate Error: " + e);
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		try {
			refreshData();
			listView();
		}
		catch (Exception e) {
			Log.e("MainActivity", "onResume Error: " + e);
		}
	}
	
	
	@Override
	public void onSideNavigationItemClick(int itemId) {
		
		try {
			String text = "";
			switch (itemId) {
			case R.id.side_navigation_menu_add:
				text="add";
				break;
			case R.id.side_navigation_menu_call:
				text="call";
				break;
			case R.id.side_navigation_menu_camera:
				text="camera";
				break;
			case R.id.side_navigation_menu_delete:
				text="delete";
				break;
			case R.id.side_navigation_menu_text:
				text="text";
				break;
			default :
				text = "";
			}
			Toast.makeText(getApplicationContext(), "Side Menu: " + text, Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			Log.e("MainActivity", "onClick Error: " + e);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		try {
			getMenuInflater().inflate(R.menu.main, menu);
		}
		catch (Exception e) {
			Log.e("MainActivity", "onCreateOptionsMenu Error: " + e);
		}
		return true;
	}
	
	
	private void listView() {
		try {
			Dao dao = new Dao(getApplicationContext());
			articleList = dao.getArticleList();
			
			ArticleAdapter articleAdapter = new ArticleAdapter(this, R.layout.photo_list_row, articleList);
			listView.setAdapter(articleAdapter);
			listView.setOnItemClickListener(this);
		}
		catch (Exception e) {
			Log.e("MainActivity", "listView Error: " + e);
		}
		
		
	}

	private final Handler HANDLER = new Handler();
	private void refreshData() {
		try {
			new Thread() {
				public void run() {
					CookieSyncManager.createInstance(getApplicationContext());
					cookieManager = CookieManager.getInstance();
					String cookie = cookieManager.getCookie(MainActivity.ACCESS_URL);
					
					Log.i("MainActivity", "Thread() - cookie: " + cookie);
					
					Proxy proxy = new Proxy(cookie);
					String url = MainActivity.ACCESS_URL+".json";
					String jsonData = proxy.getJSON(url);
					
					Log.e("MainActivity", "run - jsonData: " + jsonData);
					
					// dao
					Dao dao = new Dao(getApplicationContext());
					dao.insertJsonData(jsonData);
	
					HANDLER.post(new Runnable() {
						public void run() {
							listView();
						}
					});
				}
			}.start();
		}
		catch (Exception e) {
			Log.e("MainActivity", "refreshData Error: " + e);
		}
	}


	@Override
	public void onClick(View view) {
		try {
			switch(view.getId()) {
			case R.id.main_writing_btn : 
				Intent wrtingActivityIntent = new Intent(this, WritingActivity.class); // context, 이동할 class
				startActivity(wrtingActivityIntent);
				break;
			case R.id.main_refresh_btn :
				refreshData();
				break;
			}
		}
		catch (Exception e) {
			Log.e("MainActivity.onClick()", "onClick Error: " + e);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		try {
			Intent contentsActivityIntent = new Intent(this, ContentsActivity.class);
			Log.e("MainActivity", ".onItemClick() - position: " + position);
			
			contentsActivityIntent.putExtra("id", articleList.get(position).getId());
			startActivity(contentsActivityIntent);
		}
		catch (Exception e) {
			Log.e("MainActivity", ".onItemClick() - Error: " + e);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		try {
			String text = null;
			
			switch (item.getItemId()) {
			case R.id.action_item1 :
				text ="Action item, with text, displayed if room exists";
				break;
				
			case R.id.action_item2 :
				text = "Action item, icon only, always displayed";
				break;
			
			case R.id.action_item3 :
				text = "Normal menu item";
				break;
			
			case android.R.id.home:
				sideNavigationView.toggleMenu();
				break;
			
			default :
				return super.onOptionsItemSelected(item);
			}
			
			if (text != null) {
				Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		catch (Exception e) {
			Log.e("MainActivity.onOptionsItemSelected()", "onOptionsItemSelected Error: " + e);
		}
		return false;
		
	}

}
