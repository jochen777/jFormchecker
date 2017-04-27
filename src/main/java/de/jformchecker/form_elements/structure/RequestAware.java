package de.jformchecker.form_elements.structure;

import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public interface RequestAware {
	public void init(Request request, boolean shouldValidate, Validator validator);

}
