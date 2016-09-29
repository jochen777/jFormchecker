package de.jformchecker.validator;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.ValidationResult;


public interface Validator {

  ValidationResult validate(FormCheckerElement elem);

}
