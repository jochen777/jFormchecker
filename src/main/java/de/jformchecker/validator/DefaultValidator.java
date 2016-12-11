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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jformchecker.validator.Validator#validate(de.jformchecker.
	 * FormCheckerElement)
	 */
	@Override
	public ValidationResult validate(FormCheckerElement elem) {
		String value = elem.getValue();
		ValidationResult vr = ValidationResult.ok();
		if (value != null && !"".equals(value)) {
			vr = allCriteriaSatisfied(elem);
		} else {
			// blank input is valid if it's not required
			if (elem.isRequired()) {
				vr = ValidationResult.fail("jformchecker.required", "");
			} 
		}
		return vr;
	}

	// RFE: Maybe return here an array? So we can have more than one error-message per field.
	private ValidationResult allCriteriaSatisfied(FormCheckerElement elem) {
		for (Criterion criterion : elem.getCriteria()) {
			ValidationResult vr = criterion.validate(elem);
			if (!vr.isValid()) {
				return vr;
			}
		}

		return ValidationResult.ok();
	}


}
