package com.example.and_week2_listview1;

public class Book {
    private long id;
    private String title;
    private String author;
    private String image;
    
    public Book(long id, String title, String label) {
	this.id = id;
	this.title = title;
	this.author = label;
    }
    
    public Book(String title, String label, String image) {
	this.title = title;
	this.author = label;
	this.image = image;
    }
    
    public long getId() {
	return this.id;
    }
    
    public String getTitle() {
	return this.title;
    }
    
    public String getAuthor() {
	return this.author;
    }
    
    public String getImage() {
	return this.image;
    }
}
