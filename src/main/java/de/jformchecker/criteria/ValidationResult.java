package de.jformchecker.criteria;

/**
 * Holds the result of a validation including the possible error-message
 * 
 * @author jochen
 *
 */
public class ValidationResult {

	boolean isValid = false;

	public boolean isValid() {
		return isValid;
	}

	public String getMessage() {
		return message;
	}

	public Object[] getErrorVals() {
		return errorVals;
	}

	String message;
	Object[] errorVals;

	public ValidationResult(boolean isValid, String message, Object[] errorVals) {
		this.isValid = isValid;
		this.message = message;
		this.errorVals = errorVals;
	}

	// factory methods
	public static ValidationResult of_(boolean isValid, String message, Object... errorVals) {
		return new ValidationResult(isValid, message, errorVals);
	}
	
	public static ValidationResult fail(String message, Object... errorVals) {
		return new ValidationResult(false, message, errorVals);
	}

	
	public static ValidationResult ok() {
		// RFE: Could return always the same object! will reduce memory footprint!
		return new ValidationResult(true, "", null);
	}

}
