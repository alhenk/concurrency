package com.epam.koryagin.task;

import java.util.List;

import com.epam.koryagin.library.LibraryTaskLogic;
import com.epam.koryagin.library.Reader;
import com.epam.koryagin.library.Repository;

public final class TaskLogic {
	private TaskLogic() {
	}

	public static void runLibraryTask() {
		//Create library
		Repository library = LibraryTaskLogic.createSynchLibrary();
		//Create readers
		List<Reader> readers = LibraryTaskLogic.createListOfReaders(library);
		//Start reading
		LibraryTaskLogic.startReading(readers);
		//Wait all readers returned all books
		LibraryTaskLogic.waitAllReadersFinished(readers);
		//Print statistics
		LibraryTaskLogic.bookUsingReport(library);
	}

}
