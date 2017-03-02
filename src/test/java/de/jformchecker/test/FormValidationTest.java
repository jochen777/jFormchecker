package de.jformchecker.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.TextInput;
import de.jformchecker.request.Request;
import de.jformchecker.test.builders.ExampleFormBuilder;
import de.jformchecker.test.builders.FormCheckerBuilder;
import de.jformchecker.test.builders.RequestBuilders;

/**
 * Tests the form-validation function
 * @author jochen
 *
 */
public class FormValidationTest {
	
	@Test
	public void testFormValidation() {
		TextInput email = TextInput.build("email").setCriterias(Criteria.emailAddress());
		PasswordInput p1 = PasswordInput.build("p1");
		PasswordInput p2 = PasswordInput.build("p2");
		FormCheckerForm form = ExampleFormBuilder.getFormWithElements(RequestBuilders.FC_ID, Arrays.asList(email, p1, p2));
		form.addFormValidator((f) -> {
			if (!p1.getValue().equals(p2.getValue())) {
				p1.setInvalid();
				p2.setInvalid();
				return false;
			}
			return true;
		});
		Map<String, String> params = new HashMap<>();
		params.put("email", "test@asdf.de");
		params.put("p1", "testpw");
		params.put("p2", "testpw_different");
		Request request = RequestBuilders.buildExampleHttpRequestWithFirstRun(params);
		FormChecker fc = FormCheckerBuilder.buildFormChecker(request, form);
		fc.run();
		if (fc.isValidAndNotFirstRun()) {
			assertTrue("This form should not be true!", false);
		}
	}

}
