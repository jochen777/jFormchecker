package de.jformchecker;

import de.jformchecker.message.MessageSource;

/**
 * "global" configuration object for Formchecker use. Typically this is done
 * just once in a project.
 * 
 * @author jpier
 *
 */
public class FormCheckerConfig {
	// Translation-Sources
	MessageSource properties;

	// FormBuilder that renders the form to html
	GenericFormBuilder formBuilder;

	public FormCheckerConfig(MessageSource properties, GenericFormBuilder formBuilder) {
		super();
		this.properties = properties;
		this.formBuilder = formBuilder;
	}


	public MessageSource getProperties() {
		return properties;
	}

	public GenericFormBuilder getFormBuilder() {
		return formBuilder;
	}

}
