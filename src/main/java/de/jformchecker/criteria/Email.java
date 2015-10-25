package de.jformchecker.criteria;

/**
 * Checks that the value conforms to the email address format.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class Email extends Regex {
  private static final String REGEX = "\\p{Alnum}+@\\p{Alnum}+(\\.[A-Za-z]+)*";

  Email() {
    super(REGEX);
  }

  protected String generateErrorMessage() {
    return "Please enter a valid email address";
  }

}
