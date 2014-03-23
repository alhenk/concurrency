package com.epam.koryagin.concurrent.repository;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class DefaultLibrary extends Repository {
	private static final Logger LOGGER = Logger.getLogger(DefaultLibrary.class);

	private Set<Book> books;

	private DefaultLibrary() {
		setBooks(new HashSet<Book>());
	}

	public DefaultLibrary(Set<Book> books) {
		this.setBooks(books);
	}

	private static final class SingletonHolder {
		public static final DefaultLibrary INSTANCE = new DefaultLibrary();
	}

	public static DefaultLibrary getInstance() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public Book borrowRandomBook() throws InterruptedException {
		Book theBook = RepositoryManager.peekRandomBook(this);
		String readerID = Thread.currentThread().getName();
		while (!theBook.isAvailable()) {
			LOGGER.debug(readerID + "\t\t\t is waiting for "
					+ theBook.getTitle());
			Thread.sleep(BOOK_AVAILABILITY_POLLING_DELAY);
		}
		theBook.setAvailable(false);
		theBook.incrementReadingCounter();
		LOGGER.debug(readerID + " took the book " + theBook.getTitle());
		return theBook;
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
		while (!book.isAvailable()) {
			LOGGER.debug(readerID + "\t\t\t is waiting for "
					+ book.getTitle());
			Thread.sleep(BOOK_AVAILABILITY_POLLING_DELAY);
		}
		book.setAvailable(false);
		book.incrementReadingCounter();
		LOGGER.debug(readerID + " took the book " + book.getTitle());
	}
}
