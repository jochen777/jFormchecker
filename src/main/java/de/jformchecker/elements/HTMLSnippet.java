package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class HTMLSnippet extends AbstractInput<HTMLSnippet> implements FormCheckerElement {

	String html;

	public static HTMLSnippet build(String name) {
		HTMLSnippet i = new HTMLSnippet();
		i.name = name;
		return i;
	}

	public HTMLSnippet setHTML(String html) {
		this.html = html;
		return this;
	}

	@Override
	public String getValue() {
		return "";
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return html;
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
	}

}
