package com.epam.koryagin.concurrent.task.tunnel;

import org.apache.log4j.Logger;

/**
 * Tunnel is a path in the Tunnel Pool and it shared by trains. Trains are
 * passing it with an individual speed, given by passingThrougTime parameter
 * 
 * @author Alexandr Koryagin
 * 
 */
public class Tunnel {
	private static final Logger LOGGER = Logger.getLogger(Tunnel.class);
	private int tunnelId;

	public Tunnel(int id) {
		tunnelId = id;
	}

	public int getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}

	public void passingThrough(long passingThrougTime) {
		try {
			Thread.sleep(passingThrougTime);
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
	}

}
