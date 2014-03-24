package com.epam.koryagin.concurrent.repository;

import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.util.SemaphoreLock;

public class SemaphoreLockedLibrary extends Repository {
	private static final Logger LOGGER = Logger
			.getLogger(SemaphoreLockedLibrary.class);

	private Set<Book> books;

	private SemaphoreLockedLibrary() {
		this.books = new TreeSet<Book>();
	}

	private static final class SingletonHolder {
		public static final SemaphoreLockedLibrary INSTANCE = new SemaphoreLockedLibrary();
	}

	public static SemaphoreLockedLibrary getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * Static fabric method
	 */
	public static Repository create() {
		return new SemaphoreLockedLibrary();
	}

	@Override
	public Book borrowRandomBook() throws InterruptedException {
		Book book = BookManager.peekRandomBook(this);
		borrowBook(book);
		return book;
	}

	@Override
	public void returnBook(Book theBook) throws InterruptedException {
		SemaphoreLock.getInstance().aquireWriteLock();
		theBook.setAvailable(true);
		SemaphoreLock.getInstance().releaseWriteLock();
		String readerID = Thread.currentThread().getName();
		LOGGER.debug(readerID + " returned the book " + theBook.getTitle());
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
		boolean isTaken = false;
		while (!isTaken) {
			SemaphoreLock.getInstance().aquireWriteLock();
			if (book.isAvailable()) {
				book.setAvailable(false);
				book.incrementReadingCounter();
				SemaphoreLock.getInstance().releaseWriteLock();
				isTaken = true;
				String message = BookManager.bookUsingReportMessage(book);
				LOGGER.debug(readerID + message + book.getTitle());
			} else {
				SemaphoreLock.getInstance().releaseWriteLock();
				LOGGER.debug(readerID + "\t\t\t is waiting for "
						+ book.getTitle());
				Thread.sleep(BOOK_AVAILABILITY_POLLING_DELAY);
			}
		}
	}

}
