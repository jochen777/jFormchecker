package de.jformchecker.test;

import org.junit.Assert;
import org.junit.Test;

import de.jformchecker.Criterion;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.TextInput;

/**
 * Tests some criterias
 * 
 * @author jochen
 *
 */
public class CriteriaTests {

	@Test
	public void testMax() {
		Criterion c = Criteria.max(100);
		Assert.assertTrue("30 is smaller than 100. This should be true!", c.validate(buildSampleInput(30)).isValid());
	}

	@Test
	public void testMin() {
		Criterion c = Criteria.min(100);
		Assert.assertTrue("110 is bigger than 100. This should be true!", c.validate(buildSampleInput(110)).isValid());
	}

	@Test
	public void testStrongPassword() {
		Criterion c = Criteria.strongPassword(15);
		Assert.assertTrue("This password should be strong enough", 
				c.validate(buildSampleInput("abcAd4bcas!fegdt")).isValid());
	}
	
	@Test
	public void testEmail() {
		Criterion c = Criteria.emailAddress();
		String goodEmail = "test@test.de";
		Assert.assertTrue("This Email should be valid: " + goodEmail, 
				c.validate(buildSampleInput(goodEmail)).isValid());
		String badEmail = "asdf@asdfkd@l.de";
		Assert.assertTrue("This Email should be not valid: " + badEmail, 
				!(c.validate(buildSampleInput(badEmail)).isValid()));

	}

	
	private TextInput buildSampleInput(int val) {
		return (TextInput) TextInput.build("age").setDescription("Age").setPreSetValue(Integer.toString(val));

	}
	
	private TextInput buildSampleInput(String val) {
		return (TextInput) TextInput.build("age").setDescription("Age").setPreSetValue(val);
	}
}
