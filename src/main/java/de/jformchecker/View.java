package de.jformchecker;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	// for access objects
	Map<String, String> elements;
	Map<String, String> inputs;
	Map<String, String> labels;
	Map<String, String> helps;
	Map<String, Boolean> isError;
	// TODO: Error-msgs
	
	// RFE: try to avoid passing fc
	View(FormCheckerForm form, GenericFormBuilder formBuilder, FormChecker fc, boolean buildAccessObjects) {
		this.form = form;
		this.formBuilder = formBuilder;
		this.fc = fc;
		
		/**
		 * the access objects should only be build, if the template engine can't call methods with params.
		 * Not capable templates that need this: mustache...
		 */
		if (buildAccessObjects){
			generateAccessObjects();
		}
	}
	
	private void generateAccessObjects() {
		elements = new HashMap<>();
		inputs = new HashMap<>();
		labels = new HashMap<>();
		helps = new HashMap<>();
		isError = new HashMap<>();

		form.elements.forEach((elem) -> elements.put(elem.getName(), getElement(elem.getName())));
		form.elements.forEach((elem) -> inputs.put(elem.getName(), getInput(elem.getName())));
		form.elements.forEach((elem) -> labels.put(elem.getName(), getLabel(elem.getName())));
		form.elements.forEach((elem) -> helps.put(elem.getName(), getHelp(elem.getName())));
		form.elements.forEach((elem) -> isError.put(elem.getName(), isError(elem.getName())));
	}

	// this method is useless in most cases. You want to build your
	// input-element via a macro!
	public String getElement(String name) {
		return formBuilder.generateHtmlForElement(fc.firstRun, fc.config, form.getElement(name));
	}
	
	public List<String> getElementNames() {
		List<String> elementNames = new ArrayList<>();
		form.elements.forEach((elem) -> elementNames.add(elem.getName()));
		return elementNames;
	}

	public String getInput(String name) {
		return form.getElement(name).getInputTag();
	}

	public String getInput(String name, Map<String, String> map) {
		return form.getElement(name).getInputTag(map);
	}
	
	
	public String getType(String name) {
		return form.getElement(name).getType();
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
		StringBuilder formStart = new StringBuilder(formBuilder.generateFormStartTag(form, fc.formAction));
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
