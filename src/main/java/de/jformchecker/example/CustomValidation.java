package de.jformchecker.example;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.AbstractCriterion;
import de.jformchecker.criteria.ValidationResult;

public class CustomValidation extends AbstractCriterion {

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().equals("Jochen");
		if (!isValid) {
			return ValidationResult.fail("Always enter 'Jochen'!");
		}
		return ValidationResult.ok();
	}

}
