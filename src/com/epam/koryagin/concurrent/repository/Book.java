package com.epam.koryagin.concurrent.repository;

public class Book {
	private String title;
	private volatile boolean available;
	private int readingCounter = 0;

	public Book() {
		this.available = true;
	}

	public Book(String title) {
		this();
		this.title = title;
	}

	public Book copyBook() {
		Book copyBook = new Book(this.getTitle());
		return copyBook;
	}

	public void incrementReadingCounter() {
		readingCounter++;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getReadingCounter() {
		return readingCounter;
	}

	public void setReadingCounter(int readingCounter) {
		this.readingCounter = readingCounter;
	}
}
