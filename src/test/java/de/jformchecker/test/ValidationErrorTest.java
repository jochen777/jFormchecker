package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.elements.TextInput;
import de.jformchecker.validator.DefaultValidator;

/**
 * Tests the behaviour of validation errors
 * 
 * @author jochen
 *
 */
public class ValidationErrorTest {

	// Tests if a not translated comes
	@Test
	public void testMessageKeyError() {
		String errorMsg = "ErrorMsg"; 
		boolean translateMsg = false;
		TextInput ti = getFormElement(translateMsg, errorMsg);
		boolean firstRun = false;
		ti.init(key -> "NotCorrect", firstRun, new DefaultValidator());
		Assert.assertEquals(errorMsg, ti.getValidationResult().getMessage());
	}
	
	// Test if a translated message is coming
	@Test
	public void testTranslatedError() {
		String errorMsg = "ErrorMsg"; 
		boolean translateMsg = true;
		TextInput ti = getFormElement(translateMsg, errorMsg);
		boolean firstRun = false;
		ti.init(key -> "NotCorrect", firstRun, new DefaultValidator());
		Assert.assertEquals(errorMsg, ti.getValidationResult().getTranslatedMessage());
	}
	
	private TextInput getFormElement(boolean translatedMessage, String errorMsg) {
		TextInput ti = (TextInput) TextInput.build("firstname").setPreSetValue("Jochen");
		ti.addCriteria(el -> {
			return (!el.getValue().equals("Jochen") ? (
					translatedMessage ? ValidationResult.failWithTranslated(errorMsg) :
						ValidationResult.fail(errorMsg)
					)
					: ValidationResult.ok());
		});
		return ti;
		
	}

}
