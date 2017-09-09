package de.jformchecker.elements;

import java.util.Map;

import com.coverity.security.Escape;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.TagAttributes;
import de.jformchecker.message.MessageSource;

public class TextInput extends AbstractInput<TextInput> implements FormCheckerElement {

	private String placeholderText = "";

	public static TextInput build(String name) {
		TextInput i = new TextInput();
		i.name = name;
		return i;
	}

	public TextInput setPlaceholerText(String placeholderText) {
		this.placeholderText = placeholderText;
		return this;
	}

	@Override
	public String getInputTag(Map<String, String> attributes, MessageSource messageSource, boolean html5Validation) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);
		return String.format("<input " + buildAllAttributes(tagAttributes, messageSource, html5Validation) + buildMaxLen()
				+ "type=\"text\" name=\"%s\" value=\"%s\"" + getPlaceholder() + ">", name,
				(value == null ? "" : getValueHtmlEncoded()));
	}

	
	protected String getPlaceholder() {
		return StringUtils.isEmpty(placeholderText) ? ""
				: " placeholder=\"" + Escape.htmlText(placeholderText) + "\"";
	}
	
	@Override
	public String getType() {
		return "text";
	}


}
