package de.jformchecker.form_elements;

import de.jformchecker.form_elements.structure.FormElementModel;

public class HtmlSnippetModel implements FormElementModel{

	
	
	private static final String HTML_SNIPPET = "HTML_SNIPPET";
	String htmlContent;
	
	

	public HtmlSnippetModel(String htmlContent) {
		super();
		this.htmlContent = htmlContent;
	}

	@Override
	public String getStrValue() {
		return "HTMLSnippet";
	}

	@Override
	public String getid() {
		return HTML_SNIPPET;
	}

}
