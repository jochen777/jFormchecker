package de.jformchecker.formvalidator;

import java.util.LinkedHashMap;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.FormValidator;

public class EmptyFormValidator implements FormValidator {

  @Override
  public void validate(LinkedHashMap<String, FormCheckerElement> elements) {
    // Do nothing here.
  }

}
