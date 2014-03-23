package com.epam.koryagin.concurrent.customer;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class Reader extends Customer {
	private static final Logger LOGGER = Logger
			.getLogger(Reader.class);
	/**
	 * Default constructor with DefaultLibrary
	 */
	public Reader() {
		this.repository = DefaultLibrary.getInstance();
	}

	public Reader(Repository repository) {
		this.repository = repository;
	}
	
	public static Customer create(Repository repository) {
		return new Reader(repository);
	}

	@Override
	public void run() {
		try {
			currentBook = repository.borrowRandomBook();
			readingDelay();
			repository.returnBook(currentBook);
		} catch (InterruptedException e) {
			LOGGER.info(e);
		} 
	}
}
