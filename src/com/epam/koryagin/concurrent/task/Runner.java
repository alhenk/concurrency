package com.epam.koryagin.concurrent.task;

public class Runner {

	public static void main(String[] args) {
		
//		TaskLogic.runSynchronizedLibraryTask();
//		TaskLogic.runReadWriteLockLibraryTask();
		TaskLogic.runSemaphoreLibraryTask();
//		TaskLogic.runUnprotectedLibraryTask();
	}
}
