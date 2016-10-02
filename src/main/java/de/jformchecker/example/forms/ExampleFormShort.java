package de.jformchecker.example.forms;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.TextInput;

public class ExampleFormShort extends FormCheckerForm {

	public void init() {
		add(TextInput.build("firstname").setDescription("Your Firstname").setPreSetValue("Peter").setRequired()
				.setCriterias(Criteria.accept("Peter", "Max"), Criteria.maxLength(10)));

		add(TextInput.build("lastname").setPlaceholerText("Mustermann").setDescription("Your Lastname")
				.setHelpText("This is an example Helptext for describing this lastname field").setPreSetValue("p") // this
																													// will
																													// fail
				.setCriterias(Criteria.accept("Pan", "Mustermann")));

		disableHtml5Validation();

	}
}
