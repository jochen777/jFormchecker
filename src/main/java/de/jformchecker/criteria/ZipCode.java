package de.jformchecker.criteria;

/**
 * Checks that the value conforms to the U.S. ZIP code format.
 * 
 * @author armandino (at) gmail.com
 */
public final class ZipCode extends Regex {
  private static final String REGEX = "^\\d{5}(-\\d{4})?$";

  ZipCode() {
    super(REGEX);
  }

  protected String generateErrorMessage() {
    return "Please enter a valid ZIP code";
  }

}
