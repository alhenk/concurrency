package com.epam.koryagin.concurrent.repository;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {
	private static final long serialVersionUID = 1311733340448994514L;
	private String title;
	private volatile boolean available;
	private volatile int readingCounter = 0;
	private RestrictionType restriction;

	public Book() {
		this.restriction = RestrictionType.AVAILABLE_FOR_BORROWING;
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

	public RestrictionType getRestriction() {
		return restriction;
	}

	public void setRestriction(RestrictionType restriction) {
		this.restriction = restriction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + readingCounter;
		result = prime * result
				+ ((restriction == null) ? 0 : restriction.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (available != other.available)
			return false;
		if (readingCounter != other.readingCounter)
			return false;
		if (restriction != other.restriction)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + " hash = " + this.hashCode() + "]";
	}

	@Override
	public int compareTo(Book o) {
		return title.compareTo(o.getTitle());
	}
}
