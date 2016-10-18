package de.jformchecker.request;

/**
 * Holds the session-attributes - acts as a gateway to the servlet request for example
 * @author jochen
 *
 */
public interface Session {
	public Object getAttribute(String name);
	public void setAttribute(String name, Object o);
}
