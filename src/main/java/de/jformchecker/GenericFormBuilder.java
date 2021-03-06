package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.elements.FileUploadInput;
import de.jformchecker.message.MessageSource;
import de.jformchecker.request.Request;
import de.jformchecker.security.XSRFBuilder;

/**
 * Builds: a generic form the label-elements
 * 
 * @author jochen
 *
 */
public abstract class GenericFormBuilder {


	String submitClass = "";

	protected abstract String getHelpTag(String helpText, FormCheckerElement elem);

	public abstract TagAttributes getLabelAttributes(FormCheckerElement elem);

	public abstract Wrapper getWrapperForInput(FormCheckerElement elem);
	
	public abstract Wrapper getWrapperForAllFormElements();

	public abstract TagAttributes getFormAttributes();

	public abstract void addAttributesToInputFields(Map<String, String> attribs, FormCheckerElement elem);

	// returns the HTML code that should be given out, before and after an
	// input-element is written
	public abstract Wrapper getWrapperForElem(FormCheckerElement elem, boolean firstRun);

	public abstract ValidationResult getErrors(FormCheckerElement e, boolean firstRun);

	public abstract Wrapper getWrapperForForm(FormCheckerForm form, boolean firstRun);

	
	
	
	// Renders the html for the complete form with all elements within.
	@Deprecated
	final public String generateGenericForm(String formAction, 
			boolean firstRun, FormCheckerForm form, Request req, FormCheckerConfig config) {
		return this.generateGenericForm(formAction, firstRun, form, req, config.properties);
	}

	final public String generateGenericForm(String formAction, 
			boolean firstRun, FormCheckerForm form, Request req, MessageSource messages) {
		StringBuilder formHtml = new StringBuilder();
		Wrapper formWrapper = getWrapperForForm(form, firstRun);
		formHtml.append(formWrapper.start);
		List<FormCheckerElement> elements = form.getElements();
		
		formHtml.append(generateFormStartTag(form, formAction));
		formHtml.append(generateCSRF(req, firstRun, form));
		int lastTabIndex = 0;
		Wrapper allFormElements = getWrapperForAllFormElements();
		formHtml.append(allFormElements.start);
		for (FormCheckerElement elem : elements) {
			formHtml.append(generateHtmlForElement(firstRun, messages, elem, form.isHtml5Validation()));
			lastTabIndex = elem.getLastTabIndex();
		}
		if (form.isShowSubmitButton()) {
			Wrapper submitWrapper = getWrapperForSumit();
			formHtml.append(submitWrapper.start).append(getSubmit(lastTabIndex + 1, form.getSubmitLabel()))
					.append(submitWrapper.end);
		}
		formHtml.append(allFormElements.end);
		formHtml.append(getEndFormTag());
		formHtml.append(formWrapper.end);
		return formHtml.toString();
	}

	@Deprecated
	String generateHtmlForElement(boolean firstRun, FormCheckerConfig config, FormCheckerElement elem) {
		return this.generateHtmlForElement(firstRun, config.getProperties(), elem, false);
	}	
	// builds the html for one element
	String generateHtmlForElement(boolean firstRun, MessageSource messageSource, FormCheckerElement elem, boolean html5Validation) {
		InputElementStructure inputStruct = new InputElementStructure();
		// errors
		ValidationResult vr = getErrors(elem, firstRun);
		if (!vr.isValid()) {
			inputStruct.setErrors(formatError(messageSource.getMessage(vr)));
		}

		// label
		boolean displayLabel = !StringUtils.isEmpty(elem.getDescription());
		if (displayLabel) {
			inputStruct.setLabel(getLabelForElement(elem, getLabelAttributes(elem), firstRun));
		}
		// input tag
		Map<String, String> attribs = new LinkedHashMap<>();
		addAttributesToInputFields(attribs, elem);
		inputStruct.setInput(elem.getInputTag(attribs, messageSource, html5Validation));
		// help tag
		if (!StringUtils.isEmpty(elem.getHelpText())) {
			inputStruct.setHelp(getHelpTag(elem.getHelpText(), elem));
		}
		return getCompleteRenderedInput(inputStruct, elem, firstRun);
	}

	String generateCSRF(Request req, boolean firstRun, FormCheckerForm form) {
		if (form.isProtectedAgainstCSRF()) {
			XSRFBuilder csrfBuilder = new XSRFBuilder();
			return csrfBuilder.buildCSRFTokens(req, firstRun, form.sessionGet, form.sessionSet);
		}
		return "";
	}

	public String getHelpBlockId(FormCheckerElement elem) {
		return "helpBlock-" + elem.getName();
	}
	
	public Wrapper getWrapperForSumit() {
		return Wrapper.empty();
	}

	public abstract String formatError(String error);

	// override this, if you want to have a different order of the elements.
	public String getCompleteRenderedInput(InputElementStructure inputStruct, FormCheckerElement elem,
			boolean firstRun) {
		StringBuilder elemHtml = new StringBuilder();
		Wrapper elementWrapper = getWrapperForElem(elem, firstRun);
		elemHtml.append(elementWrapper.start);
		elemHtml.append(inputStruct.getErrors());

		Wrapper labelWrapper = getWrapperForLabel(elem);
		elemHtml.append(labelWrapper.start).append(inputStruct.getLabel()).append(labelWrapper.end);

		Wrapper inputWrapper = getWrapperForInput(elem);
		elemHtml.append(inputWrapper.start);

		elemHtml.append(inputStruct.getInput());
		elemHtml.append(inputStruct.getHelp());

		elemHtml.append(inputWrapper.end);
		elemHtml.append(elementWrapper.end);

		return elemHtml.toString();

	}

	public Wrapper getWrapperForLabel(FormCheckerElement elem) {
		return Wrapper.empty();
	}

	public boolean checkMultipart(List<FormCheckerElement> elements) {
		for (FormCheckerElement elem : elements) {
			if (elem instanceof FileUploadInput) {
				return true;
			}
		}
		return false;
	}
	
	

	public String getEndFormTag() {
		return "</form>\n";
	}



	public String generateFormStartTag(FormCheckerForm form, String formAction
			) {
		TagAttributes formTagAttributes = createFormTagAttributes(form);
		boolean isMultipart = checkMultipart(form.getElements());
		StringBuilder formStartTag = new StringBuilder();
		String id = form.getId();
		if (isMultipart) {
			formStartTag.append("<form name=\"" + id + "\" id=\"" + buildFormCSSId(id) + "\" action=\"" + formAction
					+ "\" " + AttributeUtils.buildAttributes(formTagAttributes)
					+ "  method=\""+form.getMethod()+"\" enctype=\"multipart/form-data\">\n");
		} else {
			formStartTag.append("<form name=\"" + id + "\" id=\"" + buildFormCSSId(id) + "\" "
					+ AttributeUtils.buildAttributes(formTagAttributes) + " action=\"" + formAction + "\" method=\""+form.getMethod()
					+"\" >\n");
		}
		formStartTag.append(getSubmittedTag(id));
		return formStartTag.toString();
	}
	

	public String buildFormCSSId(String id) {
		return "form-" + id;
	}

	TagAttributes createFormTagAttributes(FormCheckerForm form) {
		TagAttributes atribs = new TagAttributes();
		atribs.add(getFormAttributes());
		atribs.add(form.getFormTagAttributes());
		if (!form.html5Validation) {
			atribs.addToAttribute("novalidate", "");
		}
		return atribs;
	}

	public String getSubmit(int tabOrder, String submitLabel) {
		return "<input tabindex=\"" + tabOrder + "\" class=\"" + submitClass + "\" type=\"submit\" value=\""
				+ submitLabel + "\">\n";
	}

	public String getSubmit(String submitLabel) {
		return this.getSubmit(0, submitLabel);
	}

	public String getLabelForElement(FormCheckerElement e, TagAttributes attribs, boolean firstRun) {
		return ("<label " + AttributeUtils.buildAttributes(attribs) + " for=\"form-" + e.getName() + "\"" + " id=\""
				+ e.getName() + "-label\">" + e.getDescription() + getAddToLabel() + (e.isRequired() ? getRequiredChar() : "")
				+ "</label>");
	}

	public String getAddToLabel() {
		return ":";
	}

	public String getRequiredChar() {
		return "*";
	}

	public String getSubmittedTag(String id) {
		return "<input type=\"hidden\" name=\"" + FormChecker.SUBMIT_KEY + "\" value=\""
				+ FormChecker.SUBMIT_VALUE_PREFIX + id + "\">\n";
	}


}
