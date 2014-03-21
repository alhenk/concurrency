package com.epam.koryagin.library;

import java.util.List;

public abstract class Repository {

	public abstract Book rentRandomBook() throws InterruptedException ;
	public abstract void returnBook(Book theBook);
	public abstract void add(Book book);
	public  abstract void remove(Book book);
	public abstract List<Book> getBooks();
}
