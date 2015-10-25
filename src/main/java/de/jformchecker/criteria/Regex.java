package de.jformchecker.criteria;

import java.util.regex.Pattern;

import de.jformchecker.FormCheckerElement;


/**
 * Checks if a string matches a regular expression.
 * 
 * @author armandino (at) gmail.com
 */
public class Regex extends AbstractCriterion {
  private Pattern pattern;

  Regex(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }

  protected boolean verify(FormCheckerElement value) {
    return pattern.matcher(value.getValue()).find();
  }

  protected String generateErrorMessage() {
    return "The value must match the required format";
  }

}
