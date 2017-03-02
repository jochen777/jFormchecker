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

	public static FormChecker buildFormChecker(Request request, FormCheckerForm form) {
		FormChecker fc = FormChecker.build(request, form);
		return fc;
	}
}
