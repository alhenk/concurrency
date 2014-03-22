package com.epam.koryagin.concurrent.util;

import java.util.concurrent.Semaphore;

public class SemaphoreLock {

	private SemaphoreLock() {
	}

	private static class SingletonHolder {
		public static final SemaphoreLock INSTANCE = new SemaphoreLock();
	}

	public static SemaphoreLock getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Semaphore writeLock = new Semaphore(5);

	private Semaphore readLock = new Semaphore(10);

	public void aquireWriteLock() throws InterruptedException {
		writeLock.acquire();
	}

	public void releaseWriteLock() {
		writeLock.release();
	}

	public void aquireReadLock() throws InterruptedException {
		readLock.acquire();
	}

	public void releaseReadLock() {
		readLock.release();
	}
}
