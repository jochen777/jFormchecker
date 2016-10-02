package de.jformchecker.elements;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.validator.Validator;

public class HTMLSnippet extends AbstractInput implements FormCheckerElement {

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
	public void init(HttpServletRequest request, boolean firstRun, Validator validator) {
	}

}
