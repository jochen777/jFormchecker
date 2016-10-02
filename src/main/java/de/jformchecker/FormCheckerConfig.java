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

	public FormCheckerConfig(MessageSource properties, GenericFormBuilder formBuilder) {
		super();
		this.properties = properties;
		this.formBuilder = formBuilder;
	}

	GenericFormBuilder formBuilder;

	public MessageSource getProperties() {
		return properties;
	}

	public GenericFormBuilder getFormBuilder() {
		return formBuilder;
	}

}
