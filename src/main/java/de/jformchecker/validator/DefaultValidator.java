package de.jformchecker.validator;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.ValidationResult;

/**
 * Validator, that checks a complete form
 * 
 * @author jochen
 *
 */
public class DefaultValidator implements Validator {

	// RFE: Use optional!
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jformchecker.validator.Validator#validate(de.jformchecker.
	 * FormCheckerElement)
	 */
	@Override
	public String validate(FormCheckerElement elem) {
		boolean isValid = false;
		String errorMessage = null;
		String value = elem.getValue();
		if (value != null && !"".equals(value)) {
			ErrorMessageAndValidation val = allCriteriaSatisfied(elem);
			isValid = val.satisfied;
			errorMessage = val.errorMessage;
		} else {
			// blank input is valid if it's not required
			if (elem.isRequired()) {
				errorMessage = "required";
				isValid = false;
			} else {
				isValid = true;
			}
		}

		if (!isValid && errorMessage == null)
			errorMessage = "Invalid or missing value";

		return errorMessage;
	}

	private ErrorMessageAndValidation allCriteriaSatisfied(FormCheckerElement elem) {
		String parsedValue = elem.getValue();
		ErrorMessageAndValidation msgAndValidation = new ErrorMessageAndValidation();
		if (parsedValue == null) {
			msgAndValidation.satisfied = false;
			return msgAndValidation;
		}
		for (Criterion criterion : elem.getCriteria()) {
			ValidationResult vr = criterion.validate(elem);
			if (!vr.isValid()) {
				msgAndValidation.errorMessage = vr.getMessage();
				msgAndValidation.satisfied = false;
				return msgAndValidation;
			}
		}

		return msgAndValidation;
	}

	private class ErrorMessageAndValidation {
		String errorMessage = null;
		boolean satisfied = true;
	}

}
