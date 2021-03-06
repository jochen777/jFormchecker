package de.jformchecker.criteria;

/**
 * Holds the result of a validation including the possible error-message
 * 
 * @author jochen
 *
 */
public class ValidationResult {

	boolean isValid = false;
	
	// caching:
	private static ValidationResult okResult =  new ValidationResult(true, "", null, null);

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
	String translatedMessage;
	Object[] errorVals;

	public ValidationResult(boolean isValid, String message, Object[] errorVals, String translatedMessage) {
		this.isValid = isValid;
		this.message = message;
		this.errorVals = errorVals;
		this.translatedMessage = translatedMessage;
	}

	// factory methods
	public static ValidationResult of_(boolean isValid, String message, Object... errorVals) {
		return new ValidationResult(isValid, message, errorVals, null);
	}

	public static ValidationResult fail(String message, Object... errorVals) {
		return new ValidationResult(false, message, errorVals, null);
	}

	public static ValidationResult failWithTranslated(String message, Object... errorVals) {
		return new ValidationResult(false, null, errorVals, message);
	}

	public static ValidationResult failWithTranslated(String message) {
		return new ValidationResult(false, null, new Object[0], message);
	}
	
	public static ValidationResult ok() {
		// RFE: Could return always the same object! will reduce memory
		// footprint!
		return okResult;
	}

	public String getTranslatedMessage() {
		return translatedMessage;
	}

}
