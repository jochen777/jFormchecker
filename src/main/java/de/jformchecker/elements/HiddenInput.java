package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.AttributeUtils;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;

public class HiddenInput extends AbstractInput<HiddenInput> implements FormCheckerElement {

	public static HiddenInput build(String name) {
		HiddenInput i = new HiddenInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return "<input " + AttributeUtils.buildAttributes(tagAttributes) + buildMaxLen() + " type=\"hidden\"  name=\"" + name
				+ "\"  id=\"" + name + "\" value=\"" + (value == null ? "" : getValueHtmlEncoded()) + "\">";
	}
	
	@Override
	public String getType() {
		return "hidden";
	}


}
