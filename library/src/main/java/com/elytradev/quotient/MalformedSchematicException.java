package com.elytradev.quotient;

import java.io.IOException;

/**
 * Signals that an attempt was made to deserialize a corrupt or malformed
 * schematic file.
 */
public class MalformedSchematicException extends IOException {
	private static final long serialVersionUID = -6124378170590555271L;

	public MalformedSchematicException() {
		super();
	}

	public MalformedSchematicException(String message) {
		super(message);
	}

	public MalformedSchematicException(Throwable cause) {
		super(cause);
	}

	public MalformedSchematicException(String message, Throwable cause) {
		super(message, cause);
	}

}
