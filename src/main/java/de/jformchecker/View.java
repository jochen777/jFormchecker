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
	FormChecker fc;
	
	// RFE: try to avoid passing fc
	public View(FormCheckerForm form, GenericFormBuilder formBuilder, FormChecker fc) {
		this.form = form;
		this.formBuilder = formBuilder;
		this.fc = fc;
	}

	public String getInput(String name) {
		return form.getElement(name).getInputTag();
	}

	public String getLabel(String elementName) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(), fc.firstRun);
	}

	public String getLabel(String elementName, Map<String, String> map) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(map),
				fc.firstRun);
	}

	public String getStart() {
		StringBuilder formStart = new StringBuilder(formBuilder.generateFormStartTag(form.getId(), fc.formAction, formBuilder.checkMultipart(form.getElements()),
				formBuilder.createFormTagAttributes(form)));
		formStart.append(formBuilder.generateCSRF(fc.req, fc.firstRun, form));
		return formStart.toString();
	}
	
	public String getEnd() {
		return formBuilder.getEndFormTag();
	}
	
	public String getForm() {
		return formBuilder.generateGenericForm(fc.formAction, fc.firstRun, form, fc.req, fc.config);
	}
	
	public String getSubmit(String label) {
		String t =  formBuilder.getSubmit(label);
		return t;
	}

	public String getSubmit() {
		String t =  formBuilder.getSubmit(form.getSubmitLabel());
		return t;
	}


}
