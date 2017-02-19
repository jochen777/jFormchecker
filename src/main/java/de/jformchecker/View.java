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

	public String getInputHtml(String name) {
		return form.getElement(name).getInputTag();
	}

	public String getLabelHtml(String elementName) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(), fc.firstRun);
	}
	
	public String getLabelHtml(String elementName, Map<String, String> map) {
		return formBuilder.getLabelForElement(form.getElement(elementName), new TagAttributes(map),
				fc.firstRun);
	}

	public String getFormStart() {
		//return formBuilder.generateFormStartTag(form.getId(), fc.formAction, fc.is);
		return "";
	}
	
	public String getGenericForm() {
		return formBuilder.generateGenericForm(fc.formAction, fc.firstRun, form, fc.req, fc.config);
	}
	
	// move to ViewFacade
	public String getSubmitTag() {
		return formBuilder.getSubmittedTag(form.getId());
	}


}
