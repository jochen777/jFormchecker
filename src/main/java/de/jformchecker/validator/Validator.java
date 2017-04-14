package de.jformchecker.validator;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.ValidationResult;

// TODO: Do we need this???
public interface Validator {

	ValidationResult validate(FormCheckerElement elem);

}
