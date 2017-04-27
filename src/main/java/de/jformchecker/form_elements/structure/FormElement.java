package de.jformchecker.form_elements.structure;

import java.util.List;

import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

/**
 * Represents a form element (simple and complex) and provides the minimum interface for fc
 * @author jochen
 *
 */
public interface FormElement {
	
	// tab index -> something within the form -> solve this via an extra interface (TabIndexAware)
	// criteria ? -> nope
	
	String getId();
	
	
}

/*

Reihenfolge:

setupConfig();

checkIfFirstRun();

	
// init form and process and validate can be done in one loop!
initForm();  -> sortTabIndexes();
	
	
processAndValidateElements();
	
validateCompleteForm();
		
createView();

- xsrf
- invisble captcha (decorate submit-button)
- getValue
- ajax (validationResult)
- maxvalue
- expose javascript
- bean validation?!?


*/
