package de.jformchecker.validator;

import de.jformchecker.FormCheckerElement;


public interface Validator {

  String validate(FormCheckerElement elem);

}
