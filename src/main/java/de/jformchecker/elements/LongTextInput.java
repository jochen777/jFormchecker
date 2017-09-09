package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;
import de.jformchecker.message.MessageSource;

public class LongTextInput extends TextInput implements FormCheckerElement {

	public static LongTextInput build(String name) {
		LongTextInput i = new LongTextInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes, MessageSource messageSource, boolean html5Validation) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return "<textarea " + buildAllAttributes(tagAttributes, messageSource, html5Validation) + buildMaxLen() + " name=\"" + name + "\" id=\"" + name
				+ "\"" +getPlaceholder() + ">" + (value == null ? "" : getValueHtmlEncoded()) + "</textarea>";
	}
	
	
	@Override
	public String getType() {
		return "longtext";
	}


}
