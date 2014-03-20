package com.epam.koryagin.library;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedLibrary extends Repository{

	private List<Book> books;

	public SynchronizedLibrary() {
		books = new ArrayList<Book>();
	}

	public SynchronizedLibrary(List<Book> books) {
		this.setBooks(books);
	}

	public synchronized Book rentRandomBook(int readerID)
			throws InterruptedException {
		int booksTotalQuantaty = books.size();
		int bookIdx = (int) (Math.random() * booksTotalQuantaty);
		Book theBook = books.get(bookIdx);

		synchronized (this) {
			while (!theBook.isAvailable()) {
				System.out.println("Reader " + readerID
						+ "\t\t\t is waiting for " + theBook.getTitle());
				wait();
			}

			theBook.setAvailable(false);
			theBook.incrementReadingCounter();
			System.out.println("Reader " + readerID + " took the book "
					+ theBook.getTitle());
		}
		return theBook;
	}

	public void returnBook(int readerID, Book theBook) {
		synchronized (this) {
			theBook.setAvailable(true);
			System.out.println("Reader " + readerID + " returned the book "
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
