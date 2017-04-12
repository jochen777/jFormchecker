package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.AttributeUtils;
import de.jformchecker.FormCheckerElement;

public class HiddenInput extends AbstractInput<HiddenInput> implements FormCheckerElement {

	public static HiddenInput build(String name) {
		HiddenInput i = new HiddenInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return "<input " + AttributeUtils.buildAttributes(attributes) + buildMaxLen() + " type=\"hidden\"  name=\"" + name
				+ "\"  id=\"" + name + "\" value=\"" + (value == null ? "" : getValueHtmlEncoded()) + "\">";
	}
	
	@Override
	public String getType() {
		return "hidden";
	}


}
