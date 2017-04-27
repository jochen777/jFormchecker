package de.jformchecker.form_elements.abstracts;

import java.util.List;

import de.jformchecker.Tag;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.form_elements.structure.FormElement;
import de.jformchecker.form_elements.structure.FormElementModel;
import de.jformchecker.form_elements.structure.ModelDeliverer;
import de.jformchecker.form_elements.structure.RequestAware;
import de.jformchecker.form_elements.structure.StandardElement;
import de.jformchecker.form_elements.structure.ValidationAware;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class AbstractStandardElement implements StandardElement, FormElement, ModelDeliverer, RequestAware, ValidationAware{

	String label;
	String help;
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public Tag getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelp() {
		return help;
	}

	@Override
	public List<ValidationResult> getValid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forceValidState(ValidationResult validationResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Request request, boolean shouldValidate, Validator validator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FormElementModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
