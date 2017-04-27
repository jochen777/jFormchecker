package de.jformchecker.form_elements.abstracts;

import de.jformchecker.form_elements.structure.FormElement;

public abstract class AbstractFormElement implements FormElement{

	String id;
	
	@Override
	public String getId() {
		return id;
	}

	// RFE: Smell: Try to do immutable objects
	public void setId(String id) {
		this.id = id;
	}
}
