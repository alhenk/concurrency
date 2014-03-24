package com.epam.koryagin.concurrent.task.tonnel;

import java.util.concurrent.BlockingQueue;

import com.epam.koryagin.concurrent.util.PropertyManager;


public class Train extends Thread {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final long READING_TIME_MIN = Long.valueOf(PropertyManager
			.getValue("customer.readingTimeMin"));
	private static final long READING_TIME_MAX = Long.valueOf(PropertyManager
			.getValue("customer.readingTimeMax"));
	
	private static int counter = 0;

	private int trainID = counter++;
	private Tonnel tonnel;

	public Train() {
		this.tonnel = new Tonnel();
	}

	private Train(Tonnel tonnel) {
		this.setTonnel(tonnel);
	}

	public static Train create(Tonnel tonnel) {
		return new Train(tonnel);
	}

	public void run() {
		BlockingQueue<Train> path = tonnel.getPath();
		
		try {
			path.put(this);
			pathDelay();
			path.poll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public Tonnel getTonnel() {
		return tonnel;
	}

	public void setTonnel(Tonnel tonnel) {
		this.tonnel = tonnel;
	}

	public void pathDelay() throws InterruptedException {
		Thread.sleep((long) (READING_TIME_MIN + Math.random()
				* READING_TIME_MAX));
	}
}
