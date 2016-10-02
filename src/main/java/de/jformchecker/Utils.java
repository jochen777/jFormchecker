package de.jformchecker;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {
	public static void fillBean(List<FormCheckerElement> elements, Object bean)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for (FormCheckerElement elem : elements) {
			String key = elem.getName();
			if (PropertyUtils.isWriteable(bean, key)) {
				// RFE: Destinguish between Strings/Dates/Boolean...
				PropertyUtils.setSimpleProperty(bean, key, elem.getValue());
			}
		}
	}

	public static String buildAttributes(Map<String, String> attributes) {
		StringBuilder attrStr = new StringBuilder();
		for (String attribute : attributes.keySet()) {
			attrStr.append(Utils.buildSingleAttribute(attribute, attributes.get(attribute)));
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
		return Utils.buildAttributes(attributes.attributes);
	}

	/**
	 * Return a nicely formated form of the form for debugging or other purposes
	 */
	public static String getDebugOutput(Map<String, FormCheckerElement> elements) {
		int maxLen = 0;
		for (String key : elements.keySet()) {
			if (key.length() > maxLen) {
				maxLen = key.length();
			}
		}
		maxLen += 3;
		StringBuilder debugOutput = new StringBuilder();
		for (String key : elements.keySet()) {

			debugOutput.append(key).append(StringUtils.leftPad(":", maxLen - key.length()));
			debugOutput.append(elements.get(key).getValue()).append("\n");
		}
		return debugOutput.toString();
	}

	/**
	 * returns json output in this format: http://labs.omniti.com/labs/jsend
	 * 
	 * { "status" : "fail", "data" : { "title" : "A title is required" } }
	 *
	 * 
	 * { status : "success", data : null }
	 * 
	 * @param fc
	 * @return
	 */
	public static String getJsonOutput(FormChecker fc) {
		StringBuilder json = new StringBuilder();
		String status = fc.isValidAndNotFirstRun() ? "success" : "fail";
		json.append("{\"status\": \"" + status + "\",");
		json.append("\"data\":");

		// Problems:
		if (fc.isValidAndNotFirstRun()) {
			json.append("\"null\",");
		} else {
			// append errors:
			json.append("{");
			String commaAppend = "";
			for (FormCheckerElement elem : fc.getForm().getElements()) {
				if (!elem.isValid()) {
					json.append(commaAppend);
					json.append("\"" + elem.getName() + "\":" + "\"" + elem.getValidationResult().getMessage() + "\"");
					commaAppend = ",";
				}
			}
			json.append("},");
		}

		// no problems
		json.append("\"okdata\":");
		// append errors:
		json.append("{");
		String commaAppend = "";
		for (FormCheckerElement elem : fc.getForm().getElements()) {
			if (elem.isValid()) {
				json.append(commaAppend);
				json.append("\"" + elem.getName() + "\":" + "\"" + "OK" + "\"");
				commaAppend = ",";
			}
		}
		json.append("}");

		json.append("}");

		return json.toString();
	}
}
