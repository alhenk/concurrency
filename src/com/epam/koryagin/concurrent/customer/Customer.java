package com.epam.koryagin.concurrent.customer;

import java.util.LinkedList;
import java.util.Queue;

import com.epam.koryagin.concurrent.repository.Book;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.util.PropertyManager;

public class Customer implements Runnable {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final long READING_TIME_MIN = Long.valueOf(PropertyManager
			.getValue("customer.readingTimeMin"));
	private static final long READING_TIME_MAX = Long.valueOf(PropertyManager
			.getValue("customer.readingTimeMax"));
	private static int counter = 0;

	Repository repository;
	Book currentBook;
	Queue<Book> whishList;
	private int readerID = counter++;

	public static Customer create(Repository repository) {
		return new Customer(repository);
	}

	/**
	 * Default constructor with DefaultLibrary
	 */
	public Customer() {
		this.whishList = new LinkedList<Book>();
		this.repository = DefaultLibrary.getInstance();
	}

	public Customer(Repository repository) {
		this.repository = repository;
	}
	
	public void addWhishBook(Book book){
		whishList.add(book);
	}
	
	public Book pollWhishBook(){
		return whishList.poll();
	}
	public Book peekWhishBook(){
		return whishList.peek();
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

	public int getCustomerID() {
		return readerID;
	}

	public void readingDelay() throws InterruptedException {
		Thread.sleep((long) (READING_TIME_MIN + Math.random()
				* READING_TIME_MAX));
	}

}
