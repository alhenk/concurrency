package com.epam.koryagin.concurrent.task;

import java.util.List;

import com.epam.koryagin.concurrent.customer.CustomerAbstractFactory;
import com.epam.koryagin.concurrent.customer.ReaderFactory;
import com.epam.koryagin.concurrent.repository.Repository;
import com.epam.koryagin.concurrent.repository.SynchronizedLibrary;

public final class TaskLogic {
	private TaskLogic() {
	}

	public static void runLibraryTask() {
		// Create library
		Repository library = LibraryTaskLogic
				.createLibrary(new SynchronizedLibrary());
//		Repository library = LibraryTaskLogic
//				.createLibrary(new DefaultLibrary());
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

}
