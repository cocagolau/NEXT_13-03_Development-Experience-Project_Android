package com.example.android_photoboard;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class ProxyUP {
	
	private String lineEnd = "\r\n";
	private String twoHyphens = "--";
	private String boundary = "*****";
	
	public String uploadArticle(Article article, String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			URL url = new URL (MainActivity.ACCESS_URL+"upload.php");
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
			dos.write(getPostData("writer", article.getWriter()).getBytes("UTF-8"));
			dos.write(getPostData("id", article.getId()).getBytes("UTF-8"));
			dos.write(getPostData("content", article.getContent()).getBytes("UTF-8"));
			dos.write(getPostData("writeDate", article.getWriteDate()).getBytes("UTF-8"));
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
				Log.i("test","SERVER:"+sb.toString());
				br.close();
				return sb.toString();
			}
		}
		catch(Exception e) {
			Log.e("ProxyUP.uploadArticle()", "upload error: " + e);			
		}
		return null;
	}

	private String getPostData(String key, String value) {
		String result = null;
		try {
			result = twoHyphens + boundary + lineEnd;
			result += "Content-Disposition: form-data; name=\"" + key + "\"" + lineEnd;
			result += lineEnd;
			
			result += value;
			
			result += lineEnd;
			
			Log.i("test","DATA:"+result);
		}
		catch (Exception e) {
			Log.e("ProxyUP.getPostData()", "getPostData Error: " + e);
		}
		return result;
	}
}