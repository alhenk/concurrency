package com.epam.koryagin.concurrent.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.customer.factory.CustomerAbstractFactory;
import com.epam.koryagin.concurrent.customer.factory.ReaderFactory;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.repository.SemaphoreLockedLibrary;
import com.epam.koryagin.concurrent.repository.SynchronizedLibrary;

public final class TaskLogic {
	private static final Logger LOGGER = Logger
			.getLogger(TaskLogic.class);
	private TaskLogic() {
	}

	/**
	 * Readers are trying to get a random book
	 * from a library  and wait for it if it not available.
	 * concurrency resolved by library's synchronized methods
	 */
	public static void runSynchronizedLibraryTask() {
		LOGGER.info("RUN SYNCHRONIZED LIBRARY TASK");
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(new SynchronizedLibrary());
		// Create readers
		CustomerAbstractFactory reader = new ReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(reader);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}
	
	public static void runSemaphoreLockedLibraryTask() {
		LOGGER.info("RUN SEMAPHORE LOCKED LIBRARY TASK");
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(SemaphoreLockedLibrary.getInstance());
		// Create readers
		CustomerAbstractFactory readerFactory = new ReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(readerFactory);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}
	
	public static void runUsafeLibraryTask() {
		LOGGER.info("RUN UNSAFE LIBRARY TASK");
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(new DefaultLibrary());
		// Create readers
		CustomerAbstractFactory readerFactory = new ReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(readerFactory);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}

}
