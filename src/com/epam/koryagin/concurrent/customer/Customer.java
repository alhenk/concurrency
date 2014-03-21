package com.epam.koryagin.concurrent.customer;

import com.epam.koryagin.concurrent.repository.Book;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class Customer implements Runnable {
	private static final long READING_TIME_MIN = 100;// mS
	private static final long READING_TIME_MAX = 500;// mS
	private static int counter = 0;

	Repository repository;
	Book theBook;
	private int readerID = counter++;

	public static Customer create(Repository repository) {
		return new Customer(repository);
	}

	/**
	 * Default constructor with SychronizedLibrary
	 */
	public Customer() {
		this.repository = new DefaultLibrary();
	}

	public Customer(Repository repository) {
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

	public int getCustomerID() {
		return readerID;
	}
	
	public void readingDelay() throws InterruptedException{
		Thread.sleep((long) (READING_TIME_MIN + Math.random()
				* READING_TIME_MAX));
	}

}
