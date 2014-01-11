package com.example.android_photoboard.ado;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android_photoboard.Article;
import com.example.android_photoboard.FileDownloader;
import com.example.android_photoboard.MainActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Dao {
	private Context context;
	private SQLiteDatabase database;
	
	public Dao (Context context) {
		this.context = context;
		
		// sqlite init
		database = context.openOrCreateDatabase ("LocalDATA.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		try {
			String sql = "CREATE TABLE IF NOT EXISTS Articles ("
					+ "Id integer primary key autoincrement,"
					+ "Title text not null,"
					+ "Author text not null,"
					+ "ImgName text UNIQUE not null);";
			
			database.execSQL(sql);	
		}
		catch (Exception e) {
			Log.e("Dao.constructor", e.getMessage());
		}
	}
	
	public void insertJsonData (String jsonData) {
		int id;
		String title;
		String author;
		String imgName;
		
	
		FileDownloader fileDownloader = new FileDownloader(context);
		
		try {
			Log.e("Dao", ".insertJsonData() - parameter(jsonData):\n" + jsonData);
			
			JSONObject pbJsonObj = new JSONObject(jsonData);
			String photoBoard = pbJsonObj.getString("photoBoards");
						
			Log.e("Dao", ".insertJsonData() - photoBoard:\n" + photoBoard);
			
			JSONArray jArr = new JSONArray(photoBoard);
			JSONObject jObj;
			for (int i=0; i<jArr.length(); i++) {
				jObj = jArr.getJSONObject(i);
				

				id = jObj.getInt("id");
				title = jObj.getString("article");
				
				
				String signBoard = jObj.getString("signBoard");
				JSONObject signJsonObj = new JSONObject(signBoard);
				
				author = signJsonObj.getString("name");
				imgName = jObj.getString("filename");
				
				Log.e("Dao", ".insertJsonData() - jObj(" + i + ")\n   id: " + id + ",  title: " + title + ",  author: " + author + ",  imgName: " + imgName + "\n");
				
				String sql = "INSERT INTO Articles (Id, Title, Author, ImgName) VALUES ("
						+ id + ", '" + title + "', '" + author + "', '" + imgName + "');";
				
				try {
					database.execSQL(sql);
				}
				catch (Exception e) {
					Log.e("Dao", ".insertJsonData() - execSQL error: " + e);
				}

				fileDownloader.downFile(MainActivity.ACCESS_URL+"images/" + imgName, imgName);
				
			}
				
		}
		catch (Exception e) {
			Log.e("Dao", ".insertJsonData() - error: " + e);
		}
		
	}
	
	public ArrayList<Article> getArticleList() {
		
		ArrayList<Article> articleList = new ArrayList<Article>();
		
		int id;
		String title;
		String author;
		String imgName;
		
		try {
		
			String sql = "SELECT * FROM Articles;";
			Cursor cursor = database.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				id = cursor.getInt(0);
				title = cursor.getString(1);
				author = cursor.getString(2);
				imgName = cursor.getString(3);
				
				articleList.add( new Article(id, title, author, imgName) );
				
			}
			cursor.close();
		}
		catch (Exception e) {
			Log.e("Dao", ".getArticleList() - error: " + e);
		}
		
		
		return articleList;
	}
	
	
	public Article getArticleByArticleNumber(int id) {
		
		Article article = null;
		
		String title;
		String author;
		String imgName;
		
		try {
			String sql = "SELECT * FROM Articles WHERE Id = " + id +";";
			Cursor cursor = database.rawQuery(sql, null);
			cursor.moveToNext();
			
			id = cursor.getInt(0);
			title = cursor.getString(1);
			author = cursor.getString(2);
			imgName = cursor.getString(3);
				
			article = new Article(id, title, author, imgName);
			cursor.close();
		}
		catch (Exception e) {
			Log.e("Dao", ".getArticleByArticleName()- error: " + e);	
		}
		return article;
	}
}
