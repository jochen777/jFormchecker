package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.test.builders.ExampleFormBuilder;
import de.jformchecker.test.builders.RequestBuilders;

// Tests for setting stuff required
public class RequiredTest {

	@Test
	public void testRequiredField() {
		FormChecker fc = RequestBuilders.buildFcWithEmptyRequest();
		fc.addForm(ExampleFormBuilder.getVerySimpleForm());
		fc.run();
		Assert.assertTrue("Form should contain a required attribute!", (fc.getCompleteForm().contains("required ")));
	}

	@Test
	public void testRequiredCheckBoxField() {
		FormChecker fc = RequestBuilders.buildFcFirstrunRequest();
		String checkBoxName = "check";
		fc.addForm(ExampleFormBuilder.getFormWith1RequiredCheckbox(checkBoxName));
		fc.run();
		Assert.assertTrue("Checkbox-Element should be false, because it is required", (!fc.getForm().getElement(checkBoxName).isValid()));
	}

}
