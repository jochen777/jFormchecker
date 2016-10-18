package de.jformchecker.request;

import java.util.HashMap;
import java.util.Map;

public class SampleSession implements Session {

	Map<String, String> attributes = new HashMap<>();

	
	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public void setAttribute(String name, Object o) {
		attributes.put(name, (String)o);

	}

}
