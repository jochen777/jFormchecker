package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.elements.TextInput;
import de.jformchecker.test.builders.RequestBuilders;

public class DisableHtml5ValidationTest {

	@Test
	public void testDisabledHtml5Validation() {
		FormChecker fc = RequestBuilders.buildFcWithEmptyRequest();
		fc.addForm(new FormCheckerForm() {
			@Override
			public void init() {
				add(TextInput.build("firstname").setRequired().setPreSetValue("Horst")
						.setDescription("Your firstname"));
				add(TextInput.build("lastname").setRequired().setPreSetValue("Horst").setDescription("Your lastname"));
				disableHtml5Validation();
			}
		});
		fc.run();
		Assert.assertTrue("Form Tag should contain novalidate attribute!",
				(fc.getCompleteForm().contains(" novalidate ")));
	}
}
