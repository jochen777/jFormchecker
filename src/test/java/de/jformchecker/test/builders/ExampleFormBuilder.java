package de.jformchecker.test.builders;

import java.util.LinkedHashMap;
import java.util.List;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.CheckboxInput;
import de.jformchecker.elements.DateInput;
import de.jformchecker.elements.HiddenInput;
import de.jformchecker.elements.LongTextInput;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.elements.TextInput;

public class ExampleFormBuilder {
	public static FormCheckerForm getVerySimpleForm() {
		return new FormCheckerForm() {
			@Override
			public void init() {
				add(TextInput.build("firstname").setRequired().setPreSetValue("Horst").setHelpText("My Helptext")
						.setDescription("Your firstname"));
			}
		};
	}

	public static FormCheckerForm getVerySimpleFormWithoutHelp() {
		return new FormCheckerForm() {
			@Override
			public void init() {
				add(TextInput.build("firstname").setRequired().setPreSetValue("Horst")
						.setDescription("Your firstname"));
			}
		};
	}

	public static FormCheckerForm getFormWith1RequiredCheckbox(String checkboxname) {
		return new FormCheckerForm() {
			@Override
			public void init() {
				add(CheckboxInput.build(checkboxname).setRequired()
						.setDescription("Please check"));
			}
		};
	}

	public static FormCheckerForm getFormWithElements(String formId, List<FormCheckerElement> elements) {
		FormCheckerForm form = new FormCheckerForm() {
			
			@Override
			public String getId() {
				return formId;
			}

			@Override
			public void init() {
				elements.forEach((elem) -> add(elem));
			}
		};
		return form;
	}	
	
	public static FormCheckerForm getComplexForm(String formId) {
		
		
		return new FormCheckerForm() {

			@Override
			public String getId() {
				return formId;
			}

			@Override
			public void init() {

				add(TextInput.build("firstname").setDescription("Your Firstname").setPreSetValue("Jochen")
						.setCriterias(Criteria.accept("Jochen", "Max")));

				add(TextInput.build("lastname").setDescription("Your Lastname").setPreSetValue("pier")
						.setCriterias(Criteria.accept("Pan", "Mustermann")));

				add(TextInput.build("middelname").setDescription("Your Middelname")
						.setCriterias(new CustomValidation()));

				add(HiddenInput.build("hidden").setPreSetValue("something to remember"));

				add(DateInput.build("date").setDescription("Birthday"));

				add(PasswordInput.build("password").setDescription("Password"));

				add(LongTextInput.build("description").setDescription("Your Description"));

				LinkedHashMap<String, String> radioEntries = SampleMapBuilders.generateRadioMap();

				add(RadioInput.build("rdio").setPossibleValues(radioEntries).setDescription("Your Choice"));

				LinkedHashMap<String, String> selectEntries = SampleMapBuilders.generateSelectMap();

				add(SelectInput.build("select").setPossibleValues(selectEntries).setDescription("Your Selection"));

				add(CheckboxInput.build("check").setDescription("I order everything"));

			}

		};
	}
}
