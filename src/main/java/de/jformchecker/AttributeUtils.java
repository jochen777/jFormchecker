package de.jformchecker;

import java.util.Map;

public class AttributeUtils {


	public static String buildAttributes(Map<String, String> attributes) {
		StringBuilder attrStr = new StringBuilder();
		for (String attribute : attributes.keySet()) {
			attrStr.append(AttributeUtils.buildSingleAttribute(attribute, attributes.get(attribute)));
		}
		return attrStr.toString();
	}

	public static String buildSingleAttribute(String key, String value) {
		StringBuilder attrStr = new StringBuilder();
		if (StringUtils.isEmpty(value)) {
			attrStr.append(key);
		} else {
			attrStr.append(key).append("=\"").append(value).append("\"");
		}
		attrStr.append(" ");
		return attrStr.toString();
	}

	public static String buildAttributes(TagAttributes attributes) {
		return AttributeUtils.buildAttributes(attributes.attributes);
	}
}
