package de.jformchecker.elements;

import java.util.Map;

import com.coverity.security.Escape;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;

public class TextInput extends AbstractInput implements FormCheckerElement {

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
	public String getInputTag(Map<String, String> attributes) {
		return String.format("<input " + buildAllAttributes(attributes) + buildMaxLen()
				+ "type=\"text\" name=\"%s\" value=\"%s\"" + getPlaceholder() + ">", name,
				(value == null ? "" : getValueHtmlEncoded()));
	}

	private String getPlaceholder() {
		return StringUtils.isEmpty(placeholderText) ? ""
				: " placeholder=\"" + Escape.htmlText(placeholderText) + "\"";
	}

}
