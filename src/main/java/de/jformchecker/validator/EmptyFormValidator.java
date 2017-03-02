package de.jformchecker.validator;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.FormValidator;

public class EmptyFormValidator implements FormValidator {

	@Override
	public boolean validate(FormCheckerForm form) {
		return true;
	}

}
