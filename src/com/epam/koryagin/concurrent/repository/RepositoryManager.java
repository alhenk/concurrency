package com.epam.koryagin.concurrent.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class RepositoryManager {
	
	private RepositoryManager() {
	}
	
	public static Book peekRandomBook (Repository repository){
		Set<Book> books = repository.getBooks();
		int booksTotalQuantaty = books.size();
		int bookIdx = (int) (Math.random() * booksTotalQuantaty);
		List<Book> bookList = new ArrayList<Book>(books);
		return bookList.get(bookIdx);
	}
}
