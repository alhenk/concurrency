package com.epam.koryagin.concurrent.customer;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.repository.Book;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.util.PropertyManager;

public class Customer implements Runnable {
	private static final Logger LOGGER = Logger
			.getLogger(Customer.class);
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
	Queue<Book> wishList;
	private int readerID = counter++;

	public static Customer create(Repository repository) {
		return new Customer(repository);
	}

	/**
	 * Default constructor with DefaultLibrary
	 */
	public Customer() {
		this.wishList = new LinkedList<Book>();
		this.repository = DefaultLibrary.getInstance();
	}

	public Customer(Repository repository) {
		this.repository = repository;
	}
	
	public void addWishBook(Book book){
		wishList.add(book);
	}
	
	public Book pollWishBook(){
		return wishList.poll();
	}
	public Book peekWishBook(){
		return wishList.peek();
	}
	public void setWishBookList(Queue<Book> wishList){
		this.wishList = wishList;
	}
	public Queue<Book> getWishBookList(){
		return wishList;
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

	public int getCustomerID() {
		return readerID;
	}

	public void readingDelay() throws InterruptedException {
		Thread.sleep((long) (READING_TIME_MIN + Math.random()
				* READING_TIME_MAX));
	}

}
