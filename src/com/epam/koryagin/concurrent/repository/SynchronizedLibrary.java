package com.epam.koryagin.concurrent.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class SynchronizedLibrary extends Repository {
	private static final Logger LOGGER = Logger
			.getLogger(SynchronizedLibrary.class);
	private List<Book> books;

	public SynchronizedLibrary() {
		books = new ArrayList<Book>();
	}

	public SynchronizedLibrary(List<Book> books) {
		this.setBooks(books);
	}

	public Book rentRandomBook()
			throws InterruptedException {
		int booksTotalQuantaty = books.size();
		int bookIdx = (int) (Math.random() * booksTotalQuantaty);
		Book theBook = books.get(bookIdx);
		String readerID = Thread.currentThread().getName();
		synchronized (this) {
			while (!theBook.isAvailable()) {
				LOGGER.debug(readerID + "\t\t\t is waiting for "
						+ theBook.getTitle());
				wait();
			}

			theBook.setAvailable(false);
			theBook.incrementReadingCounter();
			LOGGER.debug(readerID + " took the book "
					+ theBook.getTitle());
		}
		return theBook;
	}

	public void returnBook(Book theBook) {
		String readerID = Thread.currentThread().getName();
		synchronized (this) {
			theBook.setAvailable(true);
			LOGGER.debug(readerID + " returned the book "
					+ theBook.getTitle());
			notifyAll();
		}
	}

	public void add(Book book) {
		books.add(book);
	}

	public void remove(Book book) {
		books.remove(book);
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
