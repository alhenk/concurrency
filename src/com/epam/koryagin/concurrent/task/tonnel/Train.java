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
	private Tunnel tunnel;

	public Train() {
		this.tunnel = new Tunnel();
	}

	private Train(Tunnel tunnel) {
		this.setTunnel(tunnel);
	}

	public static Train create(Tunnel tonnel) {
		return new Train(tonnel);
	}

	public void run() {
		BlockingQueue<Train> path = tunnel.getPath();
		
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

	public Tunnel getTunnel() {
		return tunnel;
	}

	public void setTunnel(Tunnel tunnel) {
		this.tunnel = tunnel;
	}

	public void pathDelay() throws InterruptedException {
		Thread.sleep((long) (READING_TIME_MIN + Math.random()
				* READING_TIME_MAX));
	}
}
