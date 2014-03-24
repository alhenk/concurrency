package com.epam.koryagin.concurrent.task;

public class Runner {

	public static void main(String[] args) {
		LibraryTaskLogic.runSynchronizedLibraryTask();
		LibraryTaskLogic.runSemaphoreLockedLibraryTask();
		LibraryTaskLogic.runWishBookListLibraryTask();
		LibraryTaskLogic.runUnsafeLibraryTask();
		
		TunnelTaskLogic.runTunnelTask();
	}
}
