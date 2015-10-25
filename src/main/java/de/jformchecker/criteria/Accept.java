package de.jformchecker.criteria;

import java.util.Arrays;

import de.jformchecker.FormCheckerElement;

/**
 * Checks that value is equal to one of the acceptable values.
 * 
 * Based on work of armandino (at) gmail.com
 */
public class Accept extends AbstractCriterion {
  private String[] acceptableValues;

  Accept(String... values) {
    this.acceptableValues = values;
  }

  protected final boolean verify(FormCheckerElement value) {
    for (String v : acceptableValues) {
      if (areEqual(v, value.getValue()))
        return true;
    }

    return false;
  }

  protected boolean areEqual(String v1, String v2) {
    return v1.equals(v2);
  }

  protected String generateErrorMessage() {
    return "Please enter one of the allowed values " + Arrays.asList(acceptableValues);
  }

}
