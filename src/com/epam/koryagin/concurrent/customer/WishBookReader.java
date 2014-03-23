package com.epam.koryagin.concurrent.customer;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.repository.Book;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class WishBookReader extends Customer {
	private static final Logger LOGGER = Logger.getLogger(WishBookReader.class);

	/**
	 * Default constructor with DefaultLibrary
	 */
	public WishBookReader() {
		this.repository = DefaultLibrary.getInstance();
	}

	public WishBookReader(Repository repository) {
		this.repository = repository;
	}

	public static Customer create(Repository repository) {
		return new WishBookReader(repository);
	}

	@Override
	public void run() {
		while (wishList != null && !wishList.isEmpty()) {
			Book wishBook = wishList.poll();
			try {
				repository.borrowBook(wishBook);
				readingDelay();
				repository.returnBook(wishBook);
			} catch (InterruptedException e) {
				LOGGER.info(e);
			}
		}
	}
}
