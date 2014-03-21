CONCURRENCY
===========

JAVALAB project task 4

Group of tasks Library implements different approach
in solving concurrency of multiple threads Readers
to get the resource Library, by using :

	- synchronized block on Library object;
	
	- ReadWriteReentrantLock in threads;
	
	- Semaphore on Library critical operations;
	


2014-03-21 23:32:24 INFO  TaskLogic: - RUN SEMAPHORE LOCKED LIBRARY TASK

2014-03-21 23:35:10 INFO  LibraryTaskLogic: - 

JEE was read 		167434 times

Terminator was read 		165408 times

ABC was read 		166703 times

StarWars was read 		167103 times

TheMatrix was read 		166857 times

GameOfThrones was read 		166495 times

TOTAL NUMBER OF READERS 1000000



2014-03-21 23:41:52 INFO  TaskLogic: - RUN UNPROTECTED LIBRARY TASK

2014-03-21 23:43:17 INFO  LibraryTaskLogic: -
 
JEE was read 		16667 times

Terminator was read 		16671 times

ABC was read 		16766 times

StarWars was read 		16573 times

TheMatrix was read 		16531 times

GameOfThrones was read 		16786 times

TOTAL NUMBER OF READERS 99994



2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader0 returned the book ABC

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader5 returned the book TheMatrix

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader4			 is waiting for JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader4 took the book JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader7			 is waiting for Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader7 took the book Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader1			 is waiting for JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader8			 is waiting for Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader9			 is waiting for JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader7 returned the book Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader4 returned the book JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader8			 is waiting for Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader8 took the book Terminator

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader1			 is waiting for JEE

2014-03-22 00:13:37 DEBUG DefaultLibrary: -  Reader1 took the book JEE

