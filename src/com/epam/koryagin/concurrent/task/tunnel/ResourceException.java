package com.epam.koryagin.concurrent.task.tunnel;

public class ResourceException extends Exception {
	private static final long serialVersionUID = 3562837202526925688L;

	public ResourceException() {
		super();
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceException(Throwable cause) {
		super(cause);
	}

	protected ResourceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
