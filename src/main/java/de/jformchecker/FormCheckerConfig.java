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
	private MessageSource messageSource;

	// FormBuilder that renders the form to html
	private GenericFormBuilder formBuilder;

	public FormCheckerConfig(MessageSource messageSource, GenericFormBuilder formBuilder) {
		super();
		this.messageSource = messageSource;
		this.formBuilder = formBuilder;
	}



	public MessageSource getMessageSource() {
		return messageSource;
	}

	@Deprecated
	public MessageSource getProperties() {
		return messageSource;
	}

	public GenericFormBuilder getFormBuilder() {
		return formBuilder;
	}

}
