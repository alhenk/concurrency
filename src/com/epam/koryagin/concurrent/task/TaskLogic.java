package com.epam.koryagin.concurrent.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.customer.factory.CustomerAbstractFactory;
import com.epam.koryagin.concurrent.customer.factory.LockReaderFactory;
import com.epam.koryagin.concurrent.customer.factory.ReaderFactory;
import com.epam.koryagin.concurrent.customer.factory.SemaphoreReaderFactory;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;
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
		CustomerAbstractFactory reader = new LockReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(reader);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}
	
	/**
	 * Readers are trying to get a random book
	 * from a library  and wait for it if it not available.
	 * concurrency resolved by using ReadWriteReentrantLock
	 * in the LockReader run() - methods:
	 * 		repository.rentRandomBook();
	 * 		repository.returnBook(theBook);
	 */
	public static void runReadWriteLockLibraryTask() {
		LOGGER.info("RUN READ_WRITE_REENTRANT_LOCK LIBRARY TASK");
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(new DefaultLibrary());
		// Create readers
		CustomerAbstractFactory readerFactory = new LockReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(readerFactory);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}
	
	public static void runSemaphoreLibraryTask() {
		LOGGER.info("RUN SEMAPHORE LIBRARY TASK");
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(new DefaultLibrary());
		// Create readers
		CustomerAbstractFactory readerFactory = new SemaphoreReaderFactory(library);
		List<Thread> readers = LibraryTaskLogic.createListOfReaders(readerFactory);
		// Start reading
		LibraryTaskLogic.startReading(readers);
		// Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished();
		// Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}
	
	public static void runUnprotectedLibraryTask() {
		LOGGER.info("RUN UNPROTECTED LIBRARY TASK");
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
