package com.epam.koryagin.concurrent.task;

public class Runner {

	public static void main(String[] args) {
		//have been tested on <= 5000 then OutOfMemoryError
		TaskLogic.runSynchronizedLibraryTask();
		
		//have been tested on 1000000 readers!
		TaskLogic.runSemaphoreLockedLibraryTask();
	
		TaskLogic.runUnprotectedLibraryTask();
		

	}
}
