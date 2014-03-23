package com.epam.koryagin.concurrent.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.customer.factory.CustomerAbstractFactory;
import com.epam.koryagin.concurrent.repository.Book;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.repository.RestrictionType;
import com.epam.koryagin.concurrent.util.PropertyManager;

public final class LibraryTaskLogic {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final Logger LOGGER = Logger
			.getLogger(LibraryTaskLogic.class);
	private static final int NUMBER_OF_READERS = 
			Integer.valueOf(PropertyManager.getValue("libraryTaskLogic.numberOfReaders"));
	private static final ThreadGroup libraryReader = new ThreadGroup(
			"A group of readers");
	private static final long THREAD_STATE_POLLING_DELAY = 
			Long.valueOf(PropertyManager.getValue("libraryTaskLogic.threadStatePollingDelay"));

	private LibraryTaskLogic() {
	}

	/**
	 * Create library with certain books
	 */
	public static Repository createLibrary(Repository library) {
		Book book = new Book("JEE");
		book.setRestriction(RestrictionType.AVAILABLE_FOR_BORROWING);
		library.add(book);
		book = new Book("Terminator");
		book.setRestriction(RestrictionType.READING_ROOM_ONLY);
		library.add(book);
		book =new Book("ABC");
		book.setRestriction(RestrictionType.AVAILABLE_FOR_BORROWING);
		library.add(book);
		book =new Book("StarWars");
		book.setRestriction(RestrictionType.READING_ROOM_ONLY);
		library.add(book);
		book =new Book("MatrixThe");
		book.setRestriction(RestrictionType.AVAILABLE_FOR_BORROWING);
		library.add(book);
		book =new Book("GameOfThrones");
		book.setRestriction(RestrictionType.READING_ROOM_ONLY);
		library.add(book);
		book =new Book("LordOfTheRingsThe");
		book.setRestriction(RestrictionType.AVAILABLE_FOR_BORROWING);
		library.add(book);
		return library;
	}

	/**
	 * Create list of thread readers in the group of LibraryReaders
	 */
	public static List<Thread> createListOfReaders(
			CustomerAbstractFactory customerFactory) {
		List<Thread> readers = new ArrayList<Thread>();
		for (int idx = 0; idx < NUMBER_OF_READERS; idx++) {
			readers.add(new Thread(libraryReader, customerFactory.createCustomer(), " "
					+ customerFactory.getName() + idx));
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
				Thread.sleep(THREAD_STATE_POLLING_DELAY);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Wait until all readers returned books only for debugging purpose
	 * The method activeCount() gives only estimation
	 */
	public static void waitAllReadersFinished() {
		while (libraryReader.activeCount() > 0) {
			try {
				Thread.sleep(THREAD_STATE_POLLING_DELAY);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * Print statistics of book reading totalAmount should be equal to
	 * NUMBER_OF_READERS
	 */
	public static void bookUsingReport(Repository library) {
		StringBuilder report = new StringBuilder("\n");
		int totalAmount = 0;
		for (Book book : library.getBooks()) {
			int readingCounter = book.getReadingCounter();
			totalAmount += readingCounter;
			report.append(book.getTitle()).append(" was read \t\t")
					.append(readingCounter).append(" times\n");
		}
		report.append("\nTOTAL NUMBER OF READERS ").append(totalAmount).append("\n");
		LOGGER.info(report.toString());
	}
}
