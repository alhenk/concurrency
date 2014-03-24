package com.epam.koryagin.concurrent.task;

import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.task.tunnel.Train;
import com.epam.koryagin.concurrent.task.tunnel.Tunnel;
import com.epam.koryagin.concurrent.task.tunnel.TunnelPool;
import com.epam.koryagin.concurrent.util.PropertyManager;

/**
 * JAVALAB TASK 4 LOGIC (TUNNEL) semaphore solution
 * 
 * @author Alexandr Koryagin
 */
public final class TunnelTaskLogic {
	static {
		PropertyManager.load("configure.properties");
	}
	private static final int NUMBER_OF_TRAINS = Integer.valueOf(PropertyManager
			.getValue("tunnel.numberOfTrains"));
	private static final Logger LOGGER = Logger
			.getLogger(TunnelTaskLogic.class);

	private TunnelTaskLogic() {
	}

	/**
	 * Trains are shared tunnel trying to get access during the given time
	 * waitMillisMax and drop waiting if the time is out. Concurrency resolved
	 * by semaphore
	 */
	public static void runTunnelTask() {
		LOGGER.info("RUN TUNNEL TASK");
		LinkedList<Tunnel> tunnels = new LinkedList<Tunnel>() {
			private static final long serialVersionUID = 2014245378582617460L;
			{
				this.add(new Tunnel(100));
				this.add(new Tunnel(101));
			}
		};
		TunnelPool pool = new TunnelPool(tunnels);
		for (int i = 0; i < NUMBER_OF_TRAINS; i++) {
			new Train(pool).start();
		}

	}
}
