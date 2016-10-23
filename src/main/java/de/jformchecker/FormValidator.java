package de.jformchecker;

// Validates a complete Form
@FunctionalInterface
public interface FormValidator {
	public void validate(FormCheckerForm form);
}
