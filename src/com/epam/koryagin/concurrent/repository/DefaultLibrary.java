package com.epam.koryagin.concurrent.repository;

import java.util.Set;
import java.util.TreeSet;
import org.apache.log4j.Logger;

public class DefaultLibrary extends Repository {
	private static final Logger LOGGER = Logger.getLogger(DefaultLibrary.class);

	private Set<Book> books;

	private DefaultLibrary() {
		setBooks(new TreeSet<Book>());
	}

	public DefaultLibrary(Set<Book> books) {
		this.setBooks(books);
	}

	private static final class SingletonHolder {
		public static final Repository INSTANCE = new DefaultLibrary();
	}

	/**
	 * getInstance() returns library Singleton
	 */
	public static Repository getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * Static fabric method
	 */
	public static Repository create() {
		return new DefaultLibrary();
	}

	@Override
	public Book borrowRandomBook() throws InterruptedException {
		Book book = BookManager.peekRandomBook(this);
		borrowBook(book);
		return book;
	}

	@Override
	public void returnBook(Book theBook) {
		theBook.setAvailable(true);
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
			if (book.isAvailable()) {
				book.setAvailable(false);
				book.incrementReadingCounter();
				isTaken = true;
				String message = BookManager.bookUsingReportMessage(book);
				LOGGER.debug(readerID + message + book.getTitle());
			} else {
				LOGGER.debug(readerID + "\t\t\t is waiting for "
						+ book.getTitle());
				Thread.sleep(BOOK_AVAILABILITY_POLLING_DELAY);
			}
		}
	}
}
