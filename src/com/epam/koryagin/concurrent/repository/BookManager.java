package com.epam.koryagin.concurrent.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class BookManager {

	private BookManager() {
	}

	public static Book peekRandomBook(Repository repository) {
		Set<Book> books = repository.getBooks();
		int booksTotalQuantaty = books.size();
		int bookIdx = (int) (Math.random() * booksTotalQuantaty);
		List<Book> bookList = new ArrayList<Book>(books);
		return bookList.get(bookIdx);
	}

	public static Queue<Book> createRandomWishList(Repository repository) {
		Set<Book> books = repository.getBooks();
		int booksTotalQuantaty = books.size();

		Set<Book> wishList = new HashSet<Book>();
		if (booksTotalQuantaty > 1) {
			int wishBookQuantaty = (int) (1 + Math.random()
					* booksTotalQuantaty);
			while (wishBookQuantaty > 0) {
				if (wishList.add(peekRandomBook(repository))) {
					wishBookQuantaty--;
				}
			}
		}

		return new LinkedList<Book>(wishList);
	}

	public static String bookUsingReportMessage(Book book) {
		String message;
		switch (book.getRestriction()) {
		case AVAILABLE_FOR_BORROWING:
			message = " borrowed the book ";
			break;
		case READING_ROOM_ONLY:
			message = " is in the Reading Room with the book ";
			break;
		default:
			message = "";
		}
		return message;
	}
}
