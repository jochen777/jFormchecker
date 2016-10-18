package de.jformchecker.request;

import java.util.HashMap;
import java.util.Map;

/**
 * Default Request-Implementation based on a simple map
 * @author jochen
 *
 */
public class SampleRequest implements Request{

	Map<String, String> attributes = new HashMap<>();
	Session session = new SampleSession();
	
	@Override
	public String getParameter(String key) {
		return attributes.get(key);
	}

	public void setParameter(String key, String value) {
		attributes.put(key, value);
	}

	@Override
	public Session getSession() {
		return session;
	}
	
}
