package com.epam.koryagin.concurrent.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.customer.factory.CustomerAbstractFactory;
import com.epam.koryagin.concurrent.customer.factory.ReaderFactory;
import com.epam.koryagin.concurrent.customer.factory.WishBookReaderFactory;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.LibraryManager;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.repository.SemaphoreLockedLibrary;
import com.epam.koryagin.concurrent.repository.SynchronizedLibrary;

/**
 * JAVALAB TASK 4 LOGIC (SMALL LIBRARY) collection of solutions
 * 
 * @author Alexandr Koryagin
 */
public final class LibraryTaskLogic {
	private static final Logger LOGGER = Logger
			.getLogger(LibraryTaskLogic.class);

	private LibraryTaskLogic() {
	}

	/**
	 * Readers are trying to get a random book from a library and wait for it if
	 * it not available. Concurrency resolved by library's synchronized methods
	 */
	public static void runSynchronizedLibraryTask() {
		LOGGER.info("RUN SYNCHRONIZED LIBRARY TASK");
		// Create library
		Repository library = LibraryManager.createLibrary(SynchronizedLibrary
				.create());
		// Create readers
		CustomerAbstractFactory readerFactory = new ReaderFactory(library);
		List<Thread> readers = LibraryManager
				.createListOfReaders(readerFactory);
		// Start reading
		LibraryManager.startReading(readers);
		// Wait all readers returned all books
		LibraryManager.waitAllReadersFinished();
		// Print statistics
		LibraryManager.bookUsingReport(library);
	}

	/**
	 * Readers are trying to get a random book from a library and wait for it if
	 * it not available. Concurrency resolved by semaphore
	 */
	public static void runSemaphoreLockedLibraryTask() {
		LOGGER.info("RUN SEMAPHORE LOCKED LIBRARY TASK");
		// Create library
		Repository library = LibraryManager
				.createLibrary(SemaphoreLockedLibrary.create());
		// Create readers
		CustomerAbstractFactory readerFactory = new ReaderFactory(library);
		List<Thread> readers = LibraryManager
				.createListOfReaders(readerFactory);
		// Start reading
		LibraryManager.startReading(readers);
		// Wait all readers returned all books
		LibraryManager.waitAllReadersFinished();
		// Print statistics
		LibraryManager.bookUsingReport(library);
	}

	/**
	 * Readers are trying to get books from their Wish List (random) and wait
	 * for them if they are not available. Concurrency is not resolved at all
	 * (unsafe)
	 */
	public static void runWishBookListLibraryTask() {
		LOGGER.info("RUN WISH BOOK LIST LIBRARY TASK");
		// Create library
		Repository thelibrary = LibraryManager.createLibrary(DefaultLibrary
				.create());
		// Create readers
		CustomerAbstractFactory readerFactory = new WishBookReaderFactory(
				thelibrary);
		List<Thread> readers = LibraryManager
				.createListOfReaders(readerFactory);
		// Start reading
		LibraryManager.startReading(readers);
		// Wait all readers returned all books
		LibraryManager.waitAllReadersFinished();
		// Print statistics
		LibraryManager.bookUsingReport(thelibrary);
	}

	/**
	 * Readers are trying to get a random book from a library and wait for it if
	 * it not available. Concurrency is not resolved at all (unsafe)
	 */
	public static void runUnsafeLibraryTask() {
		LOGGER.info("RUN UNSAFE LIBRARY TASK");
		// Create library
		Repository library = LibraryManager.createLibrary(DefaultLibrary
				.create());
		// Create readers
		CustomerAbstractFactory readerFactory = new ReaderFactory(library);
		List<Thread> readers = LibraryManager
				.createListOfReaders(readerFactory);
		// Start reading
		LibraryManager.startReading(readers);
		// Wait all readers returned all books
		LibraryManager.waitAllReadersFinished();
		// Print statistics
		LibraryManager.bookUsingReport(library);
	}

}
