package de.jformchecker.test.builders;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.request.Request;

// Convenience Methods to build Formchecker Objects
public class FormCheckerBuilder {
	public static FormChecker buildMinimalFormChecker(Request request) {
		FormChecker fc = new FormChecker(request);
		return fc;
	}

}
