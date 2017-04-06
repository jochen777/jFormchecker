package de.jformchecker.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.CheckboxInput;
import de.jformchecker.elements.DateInputSelectCompound;
import de.jformchecker.elements.TextInput;
import de.jformchecker.request.Request;
import de.jformchecker.test.builders.ExampleFormBuilder;
import de.jformchecker.test.builders.FormCheckerBuilder;
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
	
	
	@Test
	public void testDateSelectValidDate() {
		DateInputSelectCompound dateInputSelectCompound = DateInputSelectCompound.build("test", 2000, 2010);
		FormCheckerForm form = ExampleFormBuilder.getFormWithElements(RequestBuilders.FC_ID,  
				Arrays.asList(dateInputSelectCompound));
		Map<String, String> reqVals = new HashMap<>();
		reqVals.put("day-test", "15");
		reqVals.put("month-test", "10");
		reqVals.put("year-test", "2005");
		Request request = RequestBuilders.buildExampleHttpRequestWithFirstRun(reqVals);
		FormChecker fc = FormChecker.build(request, form);
		fc.run();
		assertTrue("date should be valid", dateInputSelectCompound.isValid());
		assertEquals(LocalDate.of(2005, 10, 15), dateInputSelectCompound.getDateValue());
	}

	@Test
	public void testDateSelectGetLegacyDateNPE() {
		DateInputSelectCompound dateInputSelectCompound = DateInputSelectCompound.build("test", 2000, 2010);
		FormCheckerForm form = ExampleFormBuilder.getFormWithElements(RequestBuilders.FC_ID,  
				Arrays.asList(dateInputSelectCompound));
		Request request = RequestBuilders.buildFirstRunEmptyHttpRequest();
		FormChecker fc = FormChecker.build(request, form);
		fc.run();
		assertEquals(null, dateInputSelectCompound.getLegacyDateValue());
		
	}

	@Test
	public void testCheckBox() {
		CheckboxInput checkBoxInput = CheckboxInput.build("test");
		FormCheckerForm form = ExampleFormBuilder.getFormWithElements(RequestBuilders.FC_ID,  
				Arrays.asList(checkBoxInput));
		Map<String, String> reqVals = new HashMap<>();
		reqVals.put("test", "test");
		Request request = RequestBuilders.buildExampleHttpRequestWithFirstRun(reqVals);
		FormChecker fc = FormChecker.build(request, form);
		fc.run();
		assertEquals(true, checkBoxInput.getBoolValue());
		
	}
	
}
