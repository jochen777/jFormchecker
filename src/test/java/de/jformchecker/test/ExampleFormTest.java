package de.jformchecker.test;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.test.builders.RequestBuilders;
import de.jformchecker.test.forms.ExampleForm;

/**
 * Tests the output of the complete form
 * @author jochen
 *
 */
public class ExampleFormTest {

	@Test
	public void testCompleteForm() {
		ExampleForm exampleForm = new ExampleForm();
		FormChecker fc = FormChecker.build(RequestBuilders.buildEmptyHttpRequest(), exampleForm);
		fc.run();
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("test/expectedHTMLExampleForm.html");
		assertEquals(convertStreamToString(in).trim(), fc.getView().getForm().trim());
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	
	@Test
	public void testCompleteFormSubmitted() {
		ExampleForm exampleForm = new ExampleForm();
		FormChecker fc = FormChecker.build(RequestBuilders.buildRequestwithFirstRun("firstname", "Horst"), exampleForm);
		fc.run();
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("test/expectedHTMLExampleForm_submitted.html");
		assertEquals(convertStreamToString(in).trim(), fc.getView().getForm().trim());
	}
}
