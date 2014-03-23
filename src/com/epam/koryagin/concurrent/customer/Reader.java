package com.epam.koryagin.concurrent.customer;

import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

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
			currentBook = repository.borrowRandomBook();
			readingDelay();
			repository.returnBook(currentBook);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
