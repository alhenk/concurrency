package com.epam.koryagin.concurrent.tunnel;

import java.util.Random;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.util.PropertyManager;

/**
 * Train is a client of the resource tunnel pool
 * 
 * @author Alexandr Koryagin
 *
 */
public class Train extends Thread {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final int PASSING_TROUGH_TIME_MAX = Integer
			.valueOf(PropertyManager.getValue("tunnel.passingTroughTimeMax"));
	private static final int PASSING_TROUGH_TIME_MIN = Integer
			.valueOf(PropertyManager.getValue("tunnel.passingTroughTimeMin"));
	private static final long WAIT_MILLIS_MAX = Long.valueOf(PropertyManager
			.getValue("tunnel.train.waitMillisMax"));
	private static final Logger LOGGER = Logger.getLogger(Train.class);
	private static int counter = 0;
	private int trainID = counter++;
	private TunnelPool pool;

	public Train(TunnelPool pool) {
		this.pool = pool;
	}

	public void run() {
		Tunnel tunnel = null;
		try {
			tunnel = pool.getResource(WAIT_MILLIS_MAX);
			long passingThrougTime = new Random().nextInt(PASSING_TROUGH_TIME_MAX)
					+ PASSING_TROUGH_TIME_MIN;
			LOGGER.debug("Train " + this.getTrainID()
					+ " is entering the tunnel " + tunnel.getTunnelId());
			tunnel.passingThrough(passingThrougTime);
		} catch (ResourceException e) {
			LOGGER.error("Train " + this.getTrainID() + " -> " + e.getMessage());
		} finally {
			if (tunnel != null) {
				LOGGER.debug("Train " + this.getTrainID()
						+ " poped out of the tunnel " + tunnel.getTunnelId());
				pool.returnResource(tunnel);
			}
		}
	}

	public int getTrainID() {
		return trainID;
	}
}
