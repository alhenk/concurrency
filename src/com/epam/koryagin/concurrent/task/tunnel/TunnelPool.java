package com.epam.koryagin.concurrent.task.tunnel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.epam.koryagin.concurrent.util.PropertyManager;
/**
 * Resource shared by trains
 * @author Alexandr Koryagin
 *
 */
public class TunnelPool {
	static {
		PropertyManager.load("configure.properties");
	}
	private final static int POOL_SIZE = Integer.valueOf(PropertyManager
			.getValue("tunnel.poolSize"));
	private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
	private final Queue<Tunnel> resources = new LinkedList<Tunnel>();
	private static final Logger LOGGER = Logger.getLogger(TunnelPool.class);

	public TunnelPool(Queue<Tunnel> source) {
		resources.addAll(source);
	}

	public Tunnel getResource(long WaitMillisMax) throws ResourceException {
		try {
			if (semaphore.tryAcquire(WaitMillisMax, TimeUnit.MILLISECONDS)) {
				Tunnel tunnel = resources.poll();
				return tunnel;
			}
		} catch (InterruptedException e) {
			LOGGER.error(e);
		}
		throw new ResourceException("Path wait time out");
	}

	public void returnResource(Tunnel tunnel) {
		resources.add(tunnel);
		semaphore.release();
	}
}
