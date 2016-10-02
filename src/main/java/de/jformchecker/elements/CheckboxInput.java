package de.jformchecker.elements;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.validator.Validator;

public class CheckboxInput extends AbstractInput implements FormCheckerElement {

	public static CheckboxInput build(String name) {
		CheckboxInput ci = new CheckboxInput();
		ci.name = name;
		return ci;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		String style = "";
		return "<input " + buildAllAttributes(attributes) + " type=\"checkbox\" name=\"" + name + "\" id=\"" + name
				+ "\" value=\"" + name + "\" " + style + " " + getCheckedStatus(name) + ">";
	}

	private String getCheckedStatus(String name) {
		if ("true".equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}

	@Override
	public void init(HttpServletRequest request, boolean firstRun, Validator validator) {
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			if (name.equals(request.getParameter(this.getName()))) {
				this.value = "true";
			} else {
				this.value = "false";
			}
			ValidationResult vr = validator.validate(this);
			if (!vr.isValid()) {
				this.valid = false;
				this.setValidationResult(vr);
			}
		}
	}

	public boolean getBoolValue() {
		if ("true".equals(this.valid)) {
			return true;
		} else {
			return false;
		}
	}
}
