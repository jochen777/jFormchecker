package de.jformchecker.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.TextInput;
import de.jformchecker.test.builders.RequestBuilders;
import de.jformchecker.validator.DefaultValidator;

// Test some form - elements and there validation
public class FormElementTests {

	@Test
	public void testNegRegexp() {
		TextInput ti = TextInput.build("test").setCriterias(Criteria.regex("\\d+"));
		ti.init(RequestBuilders.buildRequestwithFirstRun("test", "sdf"), false, new DefaultValidator());
		assertTrue("sdf should not match with \\d", !ti.isValid());
	}

	@Test
	public void testPosRegexp() {
		TextInput ti = TextInput.build("test").setCriterias(Criteria.regex("\\d+"));
		ti.init(RequestBuilders.buildRequestwithFirstRun("test", "44"), false, new DefaultValidator());
		assertTrue("sdf should not match with \\d", ti.isValid());
	}

}
