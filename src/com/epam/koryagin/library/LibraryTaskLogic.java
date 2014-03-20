package com.epam.koryagin.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public final class LibraryTaskLogic {
	private static final Logger LOGGER = Logger.getLogger(LibraryTaskLogic.class);
	private final static int NUMBER_OF_READERS = 50;

	private LibraryTaskLogic() {
	}

	/**
	 * Create library with certain books
	 */
	public static Repository createSyncLibrary() {
		SynchronizedLibrary library = new SynchronizedLibrary();
		library.add(new Book("JEE"));
		library.add(new Book("Terminator"));
		library.add(new Book("ABC"));
		library.add(new Book("StarWars"));
		library.add(new Book("TheMatrix"));
		library.add(new Book("GameOfThrones"));
		return library;
	}

	/**
	 * Create list of readers
	 */
	public static List<Thread> createListOfReaders(Repository library) {
		List<Thread> readers = new ArrayList<Thread>();
		for (int idx = 0; idx < NUMBER_OF_READERS; idx++) {
			readers.add(new Thread(Reader.create(library)));
		}
		return readers;
	}

	/**
	 * Start reading random books
	 */
	public static void startReading(List<Thread> readers) {
		for (Thread reader : readers) {
			reader.start();
		}
	}

	/**
	 * Wait until all readers returned books
	 */
	public static void waitAllReadersFinished(List<Thread> readers) {
		boolean allReadingFinished = false;
		while (!allReadingFinished) {
			allReadingFinished = true;
			for (Thread reader : readers) {
				if (reader.getState() != Thread.State.TERMINATED) {
					allReadingFinished = false;
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 *  Print statistics of book reading
	 */
	public static void bookUsingReport(Repository library) {
		StringBuilder report = new StringBuilder("\n");
		for (Book book : library.getBooks()) {
			report.append(book.getTitle())
			.append(" was read \t\t")
			.append(book.getReadingCounter())
			.append(" times\n");
		}
		LOGGER.info(report.toString());
	}

}
