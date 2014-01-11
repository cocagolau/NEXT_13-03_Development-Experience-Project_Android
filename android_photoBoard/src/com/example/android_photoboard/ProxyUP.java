package com.example.android_photoboard;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;
import android.webkit.CookieManager;


public class ProxyUP {
	
	private String lineEnd = "\r\n";
	private String twoHyphens = "--";
	private String boundary = "*****";
	
	private CookieManager cookieManager;
	
	public ProxyUP(CookieManager cookieManager) {
		this.cookieManager = cookieManager;
	}

	public String uploadArticle(Article article, String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			URL url = new URL (MainActivity.ACCESS_URL+"/m/upload");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Cache-Control", "no-cache");
			
			conn.setRequestProperty("Transfer-Encoding", "chunked");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
			dos.write(getPostData("title", article.getTitle()).getBytes("UTF-8"));
			dos.write(getPostData("author", article.getAuthor()).getBytes("UTF-8"));
			dos.write(getPostData("imgName", article.getImgName()).getBytes("UTF-8"));
			
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"" + article.getImgName() + "\"" + lineEnd);
			dos.writeBytes(lineEnd);
			Log.i("test", "filsPath:"+filePath);
			
			int bytesAvailable = fis.available();
			int maxBufferSize = 1024;
			
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			int bytesRead = fis.read(buffer, 0, bufferSize);
			
			while (bytesRead > 0){
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fis.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fis.read(buffer, 0, bufferSize);
			}
			
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			Log.i("ProxyUP.uploadArticle()", "File is written");
			
			fis.close();
			dos.flush();
			dos.close();
			
			int status = conn.getResponseCode();
			Log.i("ProxyUP.uploadArticle()", "statusUP: " + status);
			
			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line= br.readLine()) != null) {
					sb.append(line + "\n");
				}
				Log.i("ProxyUP",".uploadArticle() - server: "+sb.toString());
				br.close();
				return sb.toString();
			}
		}
		catch(Exception e) {
			Log.e("ProxyUP", ".uploadArticle() - error: " + e);			
		}
		return null;
	}
	
	public boolean login(Sign sign) {
		try {
			Log.i("ProxyUP", ".login() - sign - email: " + sign.getEmail() + ",   password: " + sign.getPassword());
			URL url = new URL (MainActivity.ACCESS_URL+"/sign/m/in");
			
//			URL url = new URL (MainActivity.ACCESS_URL+"/sign/in");
//			URL url = new URL ("http://10.73.44.93/~stu08/upload_test.php");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Transfer-Encoding", "chunked");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
			dos.write(getPostData("signInEmail", sign.getEmail()).getBytes("UTF-8"));
			dos.write(getPostData("signInPassword", sign.getPassword()).getBytes("UTF-8"));
			
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data;" + lineEnd);
			dos.writeBytes(lineEnd);
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			
			Log.i("ProxyUP", ".login() - File is written");
			dos.flush();
			dos.close();
			
			int status = conn.getResponseCode();
			Log.i("ProxyUP", ".login() - statusUP: " + status);
			
			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line= br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				
				boolean isSuccess = Boolean.parseBoolean(sb.toString().trim());
				Log.i("ProxyUP",".login() - isSuccess: "+isSuccess);
								
				String cookie = conn.getHeaderField("Set-Cookie");
				cookieManager.setCookie(MainActivity.ACCESS_URL, cookie);
				Log.i("ProxyUP", ".login() - cookie: " + cookie);
				
				return isSuccess;
							
			}
			return false;
			
		}
		catch(Exception e) {
			Log.e("ProxyUP", ".login() - error: " + e);			
		}
		return false;
	}
	
	
	public boolean signup(Sign sign) {
		try {
			Log.i("ProxyUP", ".signup() - sign - name: " + sign.getName() + ",  email: " + sign.getEmail() + ",   password: " + sign.getPassword());
			URL url = new URL (MainActivity.ACCESS_URL+"/sign");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Transfer-Encoding", "chunked");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
			dos.write(getPostData("name", sign.getName()).getBytes("UTF-8"));
			dos.write(getPostData("email", sign.getEmail()).getBytes("UTF-8"));
			dos.write(getPostData("password", sign.getPassword()).getBytes("UTF-8"));
			
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes("Content-Disposition: form-data;" + lineEnd);
			dos.writeBytes(lineEnd);
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			
			Log.i("ProxyUP", ".signup() - File is written");
			dos.flush();
			dos.close();
			
			int status = conn.getResponseCode();
			Log.i("ProxyUP", ".signup() - statusUP: " + status);
			
			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line= br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				
				boolean isSuccess = Boolean.parseBoolean(sb.toString().trim());
				Log.i("ProxyUP",".singup() - isSuccess: "+isSuccess);
								
				String cookie = conn.getHeaderField("Set-Cookie");
				cookieManager.setCookie(MainActivity.ACCESS_URL, cookie);
				Log.i("ProxyUP", ".signup() - cookie: " + cookie);
				
				return isSuccess;
							
			}
			return false;
			
		}
		catch(Exception e) {
			Log.e("ProxyUP", ".login() - error: " + e);			
		}
		return false;
	}
	
	

	private String getPostData(String key, String value) {
		String result = null;
		try {
			result = twoHyphens + boundary + lineEnd;
			result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
			result += lineEnd;
			
			result += value;
			result += lineEnd;
		}
		catch (Exception e) {
			Log.e("ProxyUP", ".getPostData() - Error: " + e);
		}
		return result;
	}

	

}