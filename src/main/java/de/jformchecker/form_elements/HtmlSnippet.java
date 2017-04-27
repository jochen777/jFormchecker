package de.jformchecker.form_elements;

import de.jformchecker.form_elements.abstracts.AbstractFormElement;
import de.jformchecker.form_elements.structure.FormElementModel;
import de.jformchecker.form_elements.structure.ModelDeliverer;

public class HtmlSnippet extends AbstractFormElement implements ModelDeliverer{

	String htmlContent;
	
	public HtmlSnippet(String htmlContent, String id) {
		this.htmlContent = htmlContent;
		this.setId(id);
	}
	
	@Override
	public FormElementModel getModel() {
		return new HtmlSnippetModel(htmlContent);
	}

}
