package com.epam.koryagin.concurrent.customer;

import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.util.SemaphoreLock;

public class Reader extends Customer {
	/**
	 * Default constructor with DefaultLibrary
	 */
	public Reader() {
		this.repository = DefaultLibrary.getInstance();
	}

	public Reader(Repository repository) {
		this.repository = repository;
	}

	@Override
	public void run() {
		try {
			theBook = repository.borrowRandomBook();
			readingDelay();
			repository.returnBook(theBook);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			SemaphoreLock.getInstance().releaseWriteLock();
		}
	}
}
