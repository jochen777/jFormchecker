package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;

public class LongTextInput extends TextInput implements FormCheckerElement {

	public static LongTextInput build(String name) {
		LongTextInput i = new LongTextInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return "<textarea " + buildAllAttributes(tagAttributes) + buildMaxLen() + " name=\"" + name + "\" id=\"" + name
				+ "\" " + ">" + (value == null ? "" : getValueHtmlEncoded()) + "</textarea>";
	}
	
	@Override
	public String getType() {
		return "longtext";
	}


}
