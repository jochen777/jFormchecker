package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.test.builders.ExampleFormBuilder;
import de.jformchecker.test.builders.RequestBuilders;

public class HelpTextTest {

	@Test
	public void testHelpText() {
		FormChecker fc = RequestBuilders.buildFcWithEmptyRequest();
		fc.addForm(ExampleFormBuilder.getVerySimpleForm());
		fc.run();
		Assert.assertTrue("Form should contain a Help-Text!", (fc.getCompleteForm().contains("My Helptext")));
		Assert.assertTrue("Form should contain aria-description!", (fc.getCompleteForm().contains("aria-describedby")));
	}

	@Test
	public void testMissingHelpText() {
		FormChecker fc = RequestBuilders.buildFcWithEmptyRequest();
		fc.addForm(ExampleFormBuilder.getVerySimpleFormWithoutHelp());
		fc.run();
		Assert.assertTrue("Form should not contain aria-description!",
				!(fc.getCompleteForm().contains("aria-describedby")));
	}

}
