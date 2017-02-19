package de.jformchecker;

import java.util.Map;

/**
 * Vacade for access to formchecker whitin templates
 * @author jochen
 *
 */
public class View {
	FormCheckerForm form;
	GenericFormBuilder formBuilder;
	boolean firstRun;
	
	public View(FormCheckerForm form, GenericFormBuilder formBuilder, boolean firstRun) {
		this.form = form;
		this.formBuilder = formBuilder;
		this.firstRun = firstRun;
	}

	public String getInput(String name) {
		return form.getElement(name).getInputTag();
	}

	public String getLabel(String name) {
		return form.getElement(name).getLabel();
	}
	
	public String getLabelTag(String elementName) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(), firstRun);
	}
	
	public String getLabelTag(String elementName, Map<String, String> map) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(map),
				firstRun);
	}

	public String getFormStart() {
		return "";//formBuilder.generateFormStartTag(, formAction, isMultipart, formTagAttributes)
	}
	
	public String getGenericForm() {
		return null;
	}
	
	// move to ViewFacade
	public String getSubmitTag() {
		return formBuilder.getSubmittedTag(form.getId());
	}


}
