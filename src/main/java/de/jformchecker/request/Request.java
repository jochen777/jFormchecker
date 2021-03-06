package de.jformchecker.request;

/**
 * Holds the request-attributes - acts as a gateway to the servlet request for example
 * @author jochen
 *
 */
@FunctionalInterface
public interface Request {
	public String getParameter(String name);
}
