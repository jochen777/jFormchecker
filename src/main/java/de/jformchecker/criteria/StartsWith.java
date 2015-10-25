package de.jformchecker.criteria;

import de.jformchecker.FormCheckerElement;

/**
 * Checks if value starts with the given string.
 * 
 * Based on work of armandino (at) gmail.com
 */
public final class StartsWith extends AbstractCriterion {
  private String[] prefixes;

  StartsWith(String... prefixes) {
    this.prefixes = prefixes;
  }

  protected boolean verify(FormCheckerElement value) {
    for (String prefix : prefixes) {
      if (value.getValue().startsWith(prefix))
        return true;
    }

    return false;
  }

  protected String generateErrorMessage() {
    return "Please enter a valid value";
  }

}
