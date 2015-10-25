package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is not less than the specified minimum.
 * 
 * @author armandino (at) gmail.com
 */
public final class MinLength extends AbstractCriterion {
  private int minLength;

  MinLength(int minLength) {
    this.minLength = minLength;
  }

  protected boolean verify(FormCheckerElement value) {
    return value.getValue().length() >= minLength;
  }

  protected String generateErrorMessage() {
    return "The value must not be less than " + minLength + " characters long";
  }

}
