package de.jformchecker.criteria;

/**
 * Checks that the value conforms to a valid Canadian postcode.
 * 
 * @author armandino (at) gmail.com
 */
public final class PostcodeCA extends Regex {
  // NOTE: single space required
  private static final String REGEX =
      "^[ABCEGHJKLMNPRSTVXYabceghjklmnprstvxy]\\d" + "[ABCEGHJKLMNPRSTVWXYZabceghjklmnprstvwxyz]\\s"
          + "\\d[ABCEGHJKLMNPRSTVWXYZabceghjklmnprstvwxyz]\\d$";

  PostcodeCA() {
    super(REGEX);
  }

  protected String generateErrorMessage() {
    return "Please enter a valid postcode";
  }

}
