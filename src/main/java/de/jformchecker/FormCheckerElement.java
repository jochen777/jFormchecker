package de.jformchecker;

import java.util.List;
import java.util.Map;

import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

/**
 * Interface for Input-Elements handled by formchecker
 * 
 * @author jochen
 *
 */
public interface FormCheckerElement {

	// RFE: check, if some methods can be protected

	// get internal name of this input-element
	public String getName();
	
	// set internal name
	public void setName(String name);


	// get the value that the user entered
	public String getValue();

	// get the value that the user entered, but html-encoded
	@Deprecated
	public String getValueHtmlEncoded();

	public void setValue(String value);

	public String getPreSetValue();

	// set an initial value to the element, before the user edited it.
	public <T extends FormCheckerElement> T setPreSetValue(String value);

	public <T extends FormCheckerElement> T setTabIndex(int tabIndex);

	public int getTabIndex();

	public int getLastTabIndex();

	// set the test in the label (builder pattern)
	
	public <T extends FormCheckerElement> T setDescription(String desc);

	// as "setDescription" but does not return anything (no builder pattern)
	public void changeDescription(String desc);

	public String getDescription();

	// returns true if element is valid
	public boolean isValid();

	public void setInvalid();

	// inits the value with the current http-request
	public void init(Request req, boolean firstrun, Validator validator);

	public ValidationResult getValidationResult();

	public void setValidationResult(ValidationResult validationResult);

	public String getInputTag();

	public String getInputTag(Map<String, String> attributes);

	public boolean isRequired();

	public <T extends FormCheckerElement> T setRequired();

	public List<Criterion> getCriteria();

	public void setFormChecker(FormChecker fc);

	// returns the complete label-html tag
	public String getLabel();

	// returns the label-html and the input-html
	public String getCompleteInput(); // RFE: Perhaps toString makes this even
										// more convenient?!

	public String getHelpText();

	// sets the size attribute
	public <T extends FormCheckerElement> T setSize(int size);

	public String getType();

}
