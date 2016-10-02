package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.Utils;

public class HiddenInput extends AbstractInput implements FormCheckerElement {

	public static HiddenInput build(String name) {
		HiddenInput i = new HiddenInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return "<input " + Utils.buildAttributes(attributes) + buildMaxLen() + " type=\"hidden\"  name=\"" + name
				+ "\"  id=\"" + name + "\" value=\"" + (value == null ? "" : getValueHtmlEncoded()) + "\">";
	}

}
