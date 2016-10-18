package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

public class SimpleLabel extends AbstractInput implements FormCheckerElement {

	public static SimpleLabel build(String name) {
		SimpleLabel i = new SimpleLabel();
		i.name = name;
		return i;
	}

	@Override
	public String getValue() {
		return this.getDescription();
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return ""; // output nothing, because headline will be outputted as
					// label
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
	}

}
