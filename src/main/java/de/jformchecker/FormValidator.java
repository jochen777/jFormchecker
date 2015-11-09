package de.jformchecker;

import java.util.LinkedHashMap;

// Validates a complete Form
public interface FormValidator {
  public void validate(LinkedHashMap<String, FormCheckerElement> elements);
}
