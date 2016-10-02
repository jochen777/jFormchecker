package de.jformchecker.security;

public class XSRFException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public XSRFException(String message) {
		super(message);
	}

}
