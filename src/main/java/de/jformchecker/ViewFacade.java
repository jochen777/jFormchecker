package de.jformchecker;

/**
 * Vacade for access to formchecker whitin templates
 * @author jochen
 *
 */
public class ViewFacade {
	FormCheckerForm form;
	GenericFormBuilder formBuilder;
	
	public ViewFacade(FormCheckerForm form, GenericFormBuilder formBuilder) {
		this.form = form;
		this.formBuilder = formBuilder;
	}

	public String getInput(String name) {
		return form.getElement(name).getInputTag();
	}

	public String getLabel(String name) {
		return form.getElement(name).getLabel();
	}
	
	public String getFormStart() {
		return "";//formBuilder.generateFormStartTag(, formAction, isMultipart, formTagAttributes)
	}
	
	public String getGenericForm() {
		return null;
	}

}
