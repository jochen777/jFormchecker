package de.jformchecker.form_elements.structure;

import java.util.List;

import de.jformchecker.criteria.ValidationResult;

public interface ValidationAware {
	public List<ValidationResult> getValid();
	
	public void forceValidState(ValidationResult validationResult);

}
