package com.epam.koryagin.concurrent.repository;

import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class SynchronizedLibrary extends Repository {
	private static final Logger LOGGER = Logger
			.getLogger(SynchronizedLibrary.class);

	private Set<Book> books;

	public SynchronizedLibrary() {
		books = new TreeSet<Book>();
	}

	public SynchronizedLibrary(Set<Book> books) {
		this.setBooks(books);
	}

	/**
	 * Static fabric method
	 */
	public static Repository create() {
		return new SynchronizedLibrary();
	}

	public Book borrowRandomBook() throws InterruptedException {
		Book book = RepositoryManager.peekRandomBook(this);
		borrowBook(book);
		return book;
	}

	public void returnBook(Book theBook) {
		String readerID = Thread.currentThread().getName();
		synchronized (this) {
			theBook.setAvailable(true);
			LOGGER.debug(readerID + " returned the book " + theBook.getTitle());
			notifyAll();
		}
	}

	public void add(Book book) {
		books.add(book.copyBook());
	}

	public void remove(Book book) {
		books.remove(book);
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public void borrowBook(Book book) throws InterruptedException {
		String readerID = Thread.currentThread().getName();
		synchronized (this) {
			while (!book.isAvailable()) {
				LOGGER.debug(readerID + "\t\t\t is waiting for "
						+ book.getTitle());
				wait();
			}
			book.setAvailable(false);
			book.incrementReadingCounter();
			String message = RepositoryManager.bookUsingReportMessage(book);
			LOGGER.debug(readerID + message + book.getTitle());
		}
	}
}
