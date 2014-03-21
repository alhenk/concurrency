package com.epam.koryagin.concurrent.repository;

import java.util.List;

import com.epam.koryagin.concurrent.util.PropertyManager;

public abstract class Repository {
	static {
		PropertyManager.load("configure.properties");
	}
	static final long AVAILABLE_POLLING_DELAY = Long.valueOf(PropertyManager
			.getValue("reposotiry.availablePollingDelay"));

	public abstract Book rentRandomBook() throws InterruptedException;

	public abstract void returnBook(Book theBook);

	public abstract void add(Book book);

	public abstract void remove(Book book);

	public abstract List<Book> getBooks();
}
