package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that the length of the value is equal to the given length.
 * 
 * @author armandino (at) gmail.com
 */
public final class ExactLength extends AbstractCriterion {
  private int length;

  ExactLength(int length) {
    this.length = length;
  }

  protected boolean verify(FormCheckerElement value) {
    return value.getValue().length() == length;
  }

  protected String generateErrorMessage() {
    return "The value must be " + length + " characters long";
  }

}
