package de.jformchecker.elements;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class SelectInput extends AbstractInput<SelectInput> implements FormCheckerElement {

	LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>(); // Linked
																			// Hashmap
																			// to
																			// maintain
																			// sort
																			// order

	public static SelectInput build(String name) {
		SelectInput i = new SelectInput();
		i.name = name;
		return i;
	}

	// convenience method
	public static SelectInput build(String name, LinkedHashMap<String, String> possibleNames) {
		SelectInput si = SelectInput.build(name);
		si.setPossibleValues(possibleNames);
		return si;
	}

	// convenience method
	public static SelectInput build(String name, String keys[], String values[]) {
		SelectInput si = SelectInput.build(name);
		if (keys.length != values.length) {
			throw new IllegalArgumentException("Key / Values with unequal length");
		}
		LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>();
		for (int i = 0; i < keys.length; i++) {
			possibleNames.put(keys[i], values[i]);
		}
		si.setPossibleValues(possibleNames);
		return si;
	}

	
	public String getInputTag(Map<String, String> attributes) {

		String inputTag = "<select " + buildAllAttributes(attributes) + " name=\"" + name + "\" >\n";
		for (String key : possibleNames.keySet()) {
			String sel = "";
			if (value != null && value.equals(key)) {
				sel = " SELECTED ";
			}
			inputTag += "<option value=\"" + key + "\"" + sel + ">" + possibleNames.get(key) + "</option>\n";
		}
		return inputTag + "</select>\n";
	}

	public SelectInput setPossibleValues(LinkedHashMap<String, String> possibleNames) {
		this.possibleNames = possibleNames;
		return this;
	}

	@Override
	public void setValue(String value) {
		if (possibleNames.containsKey(value)) {
			this.value = value;
		}
	}

}
