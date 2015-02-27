/*
 * Copyright (C) 2015, C. Ramakrishnan / Illposed Software.
 * All rights reserved.
 *
 * This code is licensed under the BSD 3-Clause license.
 * See file LICENSE (or LICENSE.html) for more information.
 */

package com.illposed.osc;

public class OSCSerializeException extends Exception {

	public OSCSerializeException() {
		super();
	}

	public OSCSerializeException(final String message) {
		super(message);
	}

	public OSCSerializeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public OSCSerializeException(final Throwable cause) {
		super(cause);
	}
}
