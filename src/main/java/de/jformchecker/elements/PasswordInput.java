package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;
import de.jformchecker.message.MessageSource;

public class PasswordInput extends TextInput implements FormCheckerElement {

	public static PasswordInput build(String name) {
		PasswordInput i = new PasswordInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes, MessageSource messageSource, boolean html5Validation) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);
		return String.format(
				"<input " + buildAllAttributes(tagAttributes) + buildMaxLen()
						+ " type=\"password\" name=\"%s\" value=\"%s\">",
				name, (value == null ? "" : getValueHtmlEncoded()));
	}

	@Override
	public String getType() {
		return "password";
	}


}
