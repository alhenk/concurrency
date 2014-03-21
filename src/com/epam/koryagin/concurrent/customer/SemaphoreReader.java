package com.epam.koryagin.concurrent.customer;

import java.util.concurrent.Semaphore;

import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class SemaphoreReader extends Customer {
	Semaphore semaphore = new Semaphore(1);
	/**
	 * Default constructor with DefaultLibrary
	 */
	public SemaphoreReader() {
		this.repository = new DefaultLibrary();
	}

	public SemaphoreReader(Repository repository) {
		this.repository = repository;
	}

	@Override
	public void run() {
		//WRONG!
	}
//		try {
//			semaphore.acquire();
//			theBook = repository.rentRandomBook();
//			semaphore.release();
//			readingDelay();
//			semaphore.acquire();
//			repository.returnBook(theBook);
//			semaphore.release();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

}
