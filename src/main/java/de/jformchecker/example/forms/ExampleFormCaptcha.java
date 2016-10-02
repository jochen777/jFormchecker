package de.jformchecker.example.forms;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.RecaptchaInput;
import de.jformchecker.elements.TextInput;

public class ExampleFormCaptcha extends FormCheckerForm {

	public void init() {
		add(TextInput.build("firstname").setDescription("Your Firstname").setPreSetValue("Peter").setRequired()
				.setCriterias(Criteria.accept("Peter", "Max"), Criteria.maxLength(10)));

		add(RecaptchaInput.build("captcha").setSiteKey("xx").setSecret("xx").setDescription("Sicherheitsabfrage"));

		disableHtml5Validation();

	}
}
