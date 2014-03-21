package com.epam.koryagin.concurrent.util;

import java.util.concurrent.Semaphore;



public class SemaphoreLock {
	
	private SemaphoreLock() {

	}

	/**
	 * Nested Class
	 */
	private static class SingletonHolder {
		public static final SemaphoreLock INSTANCE = new SemaphoreLock();
	}

	/**
	 * 
	 */
	public static SemaphoreLock getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * We allow only one thread at at time to update the cache
	 * in order to maintain consistency
	 */
	private Semaphore writeLock = new Semaphore(1);

	/**
	 * We allow 10 concurrent threads to access the cache at any given time
	 */
	private Semaphore readLock = new Semaphore(10);

	public void getWriteLock() throws InterruptedException {
		writeLock.acquire();
	}

	public void releaseWriteLock() {
		writeLock.release();
	}

	public void getReadLock() throws InterruptedException {
		readLock.acquire();
	}

	public void releaseReadLock() {
		readLock.release();
	}
}
