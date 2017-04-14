package de.jformchecker.form_elements.structure;

import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

/**
 * Represents a form element (simple and complex) and provides the minimum interface for fc
 * @author jochen
 *
 */
public interface FormElement {
	
	// tab index -> something within the form -> solve this via an extra interface (TagIndexAware)
	// criteria ? -> nope
	
	public boolean isValid();
	
	public void forceValidState(boolean valid);
	
	public Renderer getRenderer();
	
	// Do we need this firstrun thing? In case of the first run, we do not validate!
	public void init(Request request, boolean firstRun, Validator validator);
	
	
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

*/
