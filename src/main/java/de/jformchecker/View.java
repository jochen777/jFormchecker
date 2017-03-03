package de.jformchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.jformchecker.criteria.ValidationResult;

/**
 * Vacade for access to formchecker whitin templates
 * 
 * @author jochen
 *
 */
public class View {
	FormCheckerForm form;
	GenericFormBuilder formBuilder;
	FormChecker fc;

	// RFE: try to avoid passing fc
	View(FormCheckerForm form, GenericFormBuilder formBuilder, FormChecker fc) {
		this.form = form;
		this.formBuilder = formBuilder;
		this.fc = fc;
	}

	// this method is useless in most cases. You want to build your
	// input-element via a macro!
	public String getElement(String name) {
		// RFE: Use the FormBuilder Method getCompleteRenderedInput for this.
		// It's more complete

		StringBuilder element = new StringBuilder();
		element.append(this.getError(name));
		element.append(this.getLabel(name));
		element.append(this.getInput(name));
		element.append(this.getHelp(name));
		return element.toString();
	}
	
	public List<String> getElementNames() {
		List<String> elementNames = new ArrayList<>();
		form.elements.forEach((elem) -> elementNames.add(elem.getName()));
		return elementNames;
	}

	public String getInput(String name) {
		return form.getElement(name).getInputTag();
	}


	
	public boolean isError(String name) {
		return !formBuilder.getErrors(form.getElement(name), fc.firstRun).isValid();
	}

	public String getError(String name) {
		ValidationResult vr = formBuilder.getErrors(form.getElement(name), fc.firstRun);
		if (!vr.isValid()) {
			return fc.config.getProperties().getMessage(vr);
		}
		return "";
	}

	public String getLabel(String name) {
		if (!StringUtils.isEmpty(form.getElement(name).getDescription())) {
			return formBuilder.getLabelForElement(form.getElement(name), new TagAttributes(), fc.firstRun);
		} else {
			return "";
		}
	}

	public String getLabel(String name, Map<String, String> map) {
		return formBuilder.getLabelForElement(form.getElement(name), new TagAttributes(map), fc.firstRun);
	}
	
	public String getHelp(String name) {
		FormCheckerElement elem = form.getElement(name);
		if (!StringUtils.isEmpty(elem.getHelpText())) {
			return elem.getHelpText();
		}
		return "";
	}

	public String getStart() {
		StringBuilder formStart = new StringBuilder(formBuilder.generateFormStartTag(form.getId(), fc.formAction,
				formBuilder.checkMultipart(form.getElements()), formBuilder.createFormTagAttributes(form)));
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
		String t = formBuilder.getSubmit(label);
		return t;
	}

	public String getSubmit() {
		String t = formBuilder.getSubmit(form.getSubmitLabel());
		return t;
	}

}
