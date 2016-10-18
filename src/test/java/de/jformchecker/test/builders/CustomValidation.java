package de.jformchecker.test.builders;

import de.jformchecker.Criterion;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.ValidationResult;

public class CustomValidation implements Criterion {

	@Override
	public ValidationResult validate(FormCheckerElement value) {
		boolean isValid = value.getValue().equals("Jochen");
		if (!isValid) {
			return ValidationResult.fail("Always enter 'Jochen'!");
		}
		return ValidationResult.ok();
	}

}
