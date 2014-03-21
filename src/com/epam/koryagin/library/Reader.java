package com.epam.koryagin.library;

public class Reader implements Runnable {
	private static int counter = 0;

	Repository library;
	Book theBook;
	private int readerID = counter++;

	public static Reader create(Repository library) {
		return new Reader(library);
	}

	/**
	 * Default constructor 
	 * with SychronizedLibrary
	 */
	public Reader() {
		this.library = new SynchronizedLibrary();
	}

	public Reader(Repository library) {
		this.library = library;
	}

	@Override
	public void run() {
		try {
			theBook = library.rentRandomBook();
			Thread.sleep((long) (100 + Math.random() * 500));
			library.returnBook(theBook);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getReaderID() {
		return readerID;
	}
}
