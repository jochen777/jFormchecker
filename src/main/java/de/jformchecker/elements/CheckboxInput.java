package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class CheckboxInput extends AbstractInput<CheckboxInput> implements FormCheckerElement {

	public static CheckboxInput build(String name) {
		CheckboxInput ci = new CheckboxInput();
		ci.name = name;
		return ci;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		String style = "";
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return "<input " + buildAllAttributes(tagAttributes) + " type=\"checkbox\" name=\"" + name + "\" id=\"" + name
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
	public void init(Request request, boolean firstRun, Validator validator) {
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			// RFE: This is fishy. Make the validation more elegant
			if (name.equals(request.getParameter(this.getName()))) {
				this.value = "true";
			} else {
				this.value = null;
			}
			ValidationResult vr = validator.validate(this);
			if (!vr.isValid()) {
				this.valid = false;
				this.setValidationResult(vr);
			}
			if (this.value == null) {
				this.value = "false";
			}
				
		}
	}

	public boolean getBoolValue() {
		if ("true".equals(this.value)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getType() {
		return "checkbox";
	}

}
