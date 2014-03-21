package com.epam.koryagin.concurrent.customer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.epam.koryagin.concurrent.repository.DefaultLibrary;
import com.epam.koryagin.concurrent.repository.Repository;

public class LockReader extends Customer {
	private final ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * Default constructor with SychronizedLibrary
	 */
	public LockReader() {
		this.repository = new DefaultLibrary();
	}

	public LockReader(Repository repository) {
		this.repository = repository;
	}

	@Override
	public void run() {
		try {
			lock.writeLock().lock();
			theBook = repository.rentRandomBook();
			lock.writeLock().unlock();
			readingDelay();
			lock.writeLock().lock();
			repository.returnBook(theBook);
			lock.writeLock().unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
