package de.jformchecker.form_elements;

import java.util.List;

import de.jformchecker.Tag;
import de.jformchecker.form_elements.structure.FormElement;
import de.jformchecker.form_elements.structure.Renderer;
import de.jformchecker.form_elements.structure.StandardElement;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class Text implements FormElement, StandardElement{
	
	String label;
	String help;
	boolean valid;

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
	public List<String> getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public void forceValidState(boolean valid) {
		this.valid = valid;
	}

	@Override
	public Renderer getRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
		// TODO Auto-generated method stub
		
	}

}
