package com.example.android_photoboard;

public class Article {
	private int id;
	private String title;
	private String author;
	private String imgName;
	
	public Article (int id, String title, String author, String imgName) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgName = imgName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	
}