package com.epam.koryagin.concurrent.repository;

import java.util.Set;

import com.epam.koryagin.concurrent.util.PropertyManager;

public abstract class Repository {
	static {
		PropertyManager.load("configure.properties");
	}
	static final long BOOK_AVAILABILITY_POLLING_DELAY = Long
			.valueOf(PropertyManager
					.getValue("reposotiry.bookAvailabilityPollingDelay"));

	public abstract Book borrowRandomBook() throws InterruptedException;

	public abstract void returnBook(Book theBook) throws InterruptedException;

	public abstract void add(Book book);

	public abstract void remove(Book book);

	public abstract Set<Book> getBooks();
}
