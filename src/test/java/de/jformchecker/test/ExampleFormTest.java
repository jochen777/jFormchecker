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
                .getResourceAsStream("de/jformchecker/test/forms/expectedHTMLExampleForm.html");
		assertEquals(fc.getView().getForm(), convertStreamToString(in));
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
