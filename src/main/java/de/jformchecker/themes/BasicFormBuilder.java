package de.jformchecker.themes;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.GenericFormBuilder;
import de.jformchecker.TagAttributes;
import de.jformchecker.Wrapper;
import de.jformchecker.criteria.ValidationResult;

public class BasicFormBuilder extends GenericFormBuilder {

	String divSuccessClass = "has-success";
	String divErrorClass = "has-error";

	protected String getHelpTag(String helpText, FormCheckerElement elem) {
		return "<span id=\"" + this.getHelpBlockId(elem) + "\" class=\"help-block\">" + helpText + "</span>";
	}

	public TagAttributes getLabelAttributes(FormCheckerElement elem) {
		TagAttributes attributes = new TagAttributes();
		attributes.put("class", "control-label");
		return attributes;
	}

	public Wrapper getWrapperForInput(FormCheckerElement elem) {
		return Wrapper.ofTag("div");
	}

	public TagAttributes getFormAttributes() {
		TagAttributes attributes = new TagAttributes();
		return attributes;
	}

	public void addAttributesToInputFields(Map<String, String> attribs, FormCheckerElement elem) {
		attribs.put("class", "form-control");
	}

	public Wrapper getWrapperForElem(FormCheckerElement elem, boolean firstRun) {
		String state = "";
		if (!firstRun) {
			if (!elem.isValid()) {
				state = " " + divErrorClass;
			} else {
				state = " " + divSuccessClass;
			}

		}
		return new Wrapper("<div class=\"form-group" + state + "\">", "</div>");
	}

	final public void setDivSuccessClass(String divSuccessClass) {
		this.divSuccessClass = divSuccessClass;
	}

	final public void setDivErrorClass(String divErrorClass) {
		this.divErrorClass = divErrorClass;
	}

	@Override
	public ValidationResult getErrors(FormCheckerElement e, boolean firstRun) {
		if (!firstRun && !e.isValid()) {
			return (e.getValidationResult());
		}
		return ValidationResult.ok();
	}

	@Override
	public String formatError(String error) {
		if (error != null && !"".equals(error)) {
			return ("Problem: " + error + "!!<br>");
		} else {
			return "";
		}
	}

	@Override
	public Wrapper getWrapperForAllFormElements() {
		return Wrapper.ofTag("fieldset");
	}

	@Override
	public Wrapper getWrapperForForm(FormCheckerForm form, boolean firstRun) {
		return Wrapper.empty();
	}

}
