package com.epam.koryagin.concurrent.customer;

import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class Reader extends Customer {
	/**
	 * Default constructor with SychronizedLibrary
	 */
	public Reader() {
		this.repository = new DefaultLibrary();
	}

	public Reader(Repository repository) {
		this.repository = repository;
	}

	@Override
	public void run() {
		try {
			theBook = repository.rentRandomBook();
			readingDelay();
			repository.returnBook(theBook);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
