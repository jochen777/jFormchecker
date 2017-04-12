package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// class that holds html tag-attributes
public class TagAttributes {
	LinkedHashMap<String, String> attributes;

	public TagAttributes(Map<String, String> attribs) {
		attributes = new LinkedHashMap<>(attribs);
	}

	public TagAttributes(LinkedHashMap<String, String> attribs) {
		attributes = attribs;
	}

	public TagAttributes() {
		this(new LinkedHashMap<>());
	}

	public TagAttributes(String key, String value) {
		this();
		this.put(key, value);
	}

	public TagAttributes put(String key, String value) {
		attributes.put(key, value);
		return this;
	}

	public String get(String key) {
		return attributes.get(key);
	}

	public void addToAttribute(String key, String value) {
		if (!attributes.containsKey(key)) {
			attributes.put(key, value);
		} else {
			attributes.put(key, attributes.get(key) + value);
		}
	}

	public Set<String> keySet() {
		return attributes.keySet();
	}

	public void add(TagAttributes formAttributes) {
		formAttributes.attributes.forEach((key, value)->
			this.addToAttribute(key, value)
		);
	}

	public void add(LinkedHashMap<String, String> attribs) {
		attribs.forEach((key, value) -> 
			this.addToAttribute(key, value)
		);
	}

}
