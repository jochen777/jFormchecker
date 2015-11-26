package de.jformchecker.example;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.criteria.AbstractCriterion;

public class CustomValidation extends AbstractCriterion {

  @Override
  protected boolean verify(FormCheckerElement value) {
    if (value.getValue().equals("Jochen"))
      return true;
    return false;
  }

  @Override
  protected String generateErrorMessage() {
    return "Always enter 'Jochen'!";
  }

}
