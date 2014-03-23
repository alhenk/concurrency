package com.epam.koryagin.concurrent.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class DefaultLibrary extends Repository {
	private static final Logger LOGGER = Logger
			.getLogger(DefaultLibrary.class);

	
	private List<Book> books;

	private DefaultLibrary() {
		setBooks(new ArrayList<Book>());
	}

	public DefaultLibrary(List<Book> books) {
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
		int booksTotalQuantaty = books.size();
		int bookIdx = (int) (Math.random() * booksTotalQuantaty);
		Book theBook = books.get(bookIdx);
		String readerID = Thread.currentThread().getName();
		
		while (!theBook.isAvailable()) {
			LOGGER.debug(readerID + "\t\t\t is waiting for "
					+ theBook.getTitle());
			Thread.sleep(AVAILABLE_POLLING_DELAY);
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
