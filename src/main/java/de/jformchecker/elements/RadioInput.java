package de.jformchecker.elements;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.AttributeUtils;
import de.jformchecker.FormCheckerElement;

public class RadioInput extends AbstractInput<RadioInput> implements FormCheckerElement {

	LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>(); // Linked
																			// Hashmap
																			// to
																			// maintain
																			// sort
																			// order
	
	public static RadioInput build(String name) {
		RadioInput i = new RadioInput();
		i.name = name;
		return i;
	}

	// convenience method
	public static RadioInput build(String name, LinkedHashMap<String, String> possibleNames) {
		RadioInput si = RadioInput.build(name);
		si.setPossibleValues(possibleNames);
		return si;
	}

	// convenience method
	public static RadioInput build(String name, String keys[], String values[]) {
		RadioInput si = RadioInput.build(name);
		if (keys.length != values.length) {
			throw new IllegalArgumentException("Key / Values with unequal lenght");
		}
		LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>();
		for (int i = 0; i < keys.length; i++) {
			possibleNames.put(keys[i], values[i]);
		}
		si.setPossibleValues(possibleNames);
		return si;
	}

	public String getInputTag(Map<String, String> attributes) {
		StringBuilder inputTag = new StringBuilder();
		possibleNames.forEach((key, value) -> {
			// leer - bedeutet: Radio - Button ist optional, also nicht als
			// radio ausgeben!
			if (!"".equals(value)) {
				inputTag.append(this.getInputTag(key, attributes) + " <label for=\"form-radio-" + name + "-" + key + "\" class=\""
						+ "" + "\" id=\"label-" + name + "-" + key + "\">" + value + " </label>\n");
			}
			// do not increase tab-index:
			// http://stackoverflow.com/questions/14322564/can-you-tab-through-all-radio-buttons
			
		});
		return inputTag.toString();
	}

	public String getInputTag(String curValue, Map<String, String> attributes) {
		return "<input id=\"form-radio-" + name + "-" + curValue + "\" " + AttributeUtils.buildAttributes(attributes)
				+ getTabIndexTag() + " type=\"radio\" name=\"" + name + "\"  value=\"" + curValue + "\" "
				+ getCheckedStatus(curValue) + "" + " " + " >\n";
	}

	private String getCheckedStatus(String _name) {
		if (value != null && value.equals(_name)) {
			return "checked";
		} else {
			return "";
		}
	}

	@Override
	public int getLastTabIndex() {
		return this.getTabIndex();
	}

	public RadioInput setPossibleValues(LinkedHashMap<String, String> possibleNames) {
		this.possibleNames = possibleNames;
		return this;
	}

	@Override
	public void setValue(String value) {
		if (possibleNames.containsKey(value)) {
			this.value = value;
		}
	}
	
	@Override
	public String getType() {
		return "radio";
	}


}
