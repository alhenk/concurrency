package com.epam.koryagin.concurrent.repository;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.util.SemaphoreLock;

public class SemaphoreLockedLibrary extends Repository {
	private static final Logger LOGGER = Logger
			.getLogger(SemaphoreLockedLibrary.class);

	private List<Book> books;

	private SemaphoreLockedLibrary() {
		this.books = new ArrayList<Book>();
	}

	private static final class SingletonHolder {
		public static final SemaphoreLockedLibrary INSTANCE = new SemaphoreLockedLibrary();
	}

	public static SemaphoreLockedLibrary getInstance() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public Book rentRandomBook() throws InterruptedException {
		int booksTotalAmount = books.size();
		int bookIdx = (int) (Math.random() * booksTotalAmount);
		Book theBook = books.get(bookIdx);
		String readerID = Thread.currentThread().getName();

		boolean isTaken = false;
		while (!isTaken) {

			SemaphoreLock.getInstance().aquireWriteLock();
			if (theBook.isAvailable()) {
				theBook.setAvailable(false);
				theBook.incrementReadingCounter();
				SemaphoreLock.getInstance().releaseWriteLock();
				isTaken = true;
				LOGGER.debug(readerID + " took the book " + theBook.getTitle());
			} else {
				SemaphoreLock.getInstance().releaseWriteLock();
				LOGGER.debug(readerID + "\t\t\t is waiting for "
						+ theBook.getTitle());
				Thread.sleep(AVAILABLE_POLLING_DELAY);
			}
		}
		return theBook;
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
