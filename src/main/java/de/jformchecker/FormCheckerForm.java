package de.jformchecker;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.jformchecker.message.MessageSource;
import de.jformchecker.request.SessionGet;
import de.jformchecker.request.SessionSet;

// holds a collection of form-Elements that can be rendered by formchecker
public abstract class FormCheckerForm {

	List<FormCheckerElement> elements = new ArrayList<>();
	List<FormValidator> validators = new ArrayList<>();
	private Map<String, FormCheckerElement> fastAccess = new LinkedHashMap<>();
	String submitLabel = "OK";
	private MessageSource messageSource;
	
	boolean protectedAgainstCSRF = false;
	boolean showSubmitButton = true;
	
	SessionSet sessionSet;
	SessionGet sessionGet;
	
	String id="id";	// default

	boolean html5Validation = true;

	Method method = Method.POST;


	public String getSubmitLabel() {
		return submitLabel;
	}
	

	public void setSubmitLabel(String submitLabel) {
		this.submitLabel = submitLabel;
	}

	private TagAttributes formTagAttributes = new TagAttributes();

	public TagAttributes getFormTagAttributes() {
		return formTagAttributes;
	}

	public void setFormTagAttributes(LinkedHashMap<String, String> formTagAttributes) {
		this.formTagAttributes = new TagAttributes(formTagAttributes);
	}

	public Map<String, FormCheckerElement> getElementsAsMap() {
		return fastAccess;
	}



	// Should be overriden
	public abstract void init();

	public void disableHtml5Validation() {
		html5Validation = false;
	}

	public List<FormValidator> getValidators() {
		return validators;
	}

	public FormCheckerForm add(FormCheckerElement elem) {
		// RFE: Exception, if elem is added twice!
		elements.add(elem);
		fastAccess.put(elem.getName(), elem);
		return this;
	}

	public List<FormCheckerElement> getElements() {
		return elements;
	}

	public FormCheckerForm addFormValidator(FormValidator formValidator) {
		validators.add(formValidator);
		return this;
	}

	public FormCheckerElement getElement(String name) {
		return fastAccess.get(name);
	}


	public MessageSource getMessageSource() {
		return messageSource;
	}


	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	public boolean isProtectedAgainstCSRF() {
		return protectedAgainstCSRF;
	}


	public void setProtectedAgainstCSRF(boolean protectedAgainstCSRF) {
		this.protectedAgainstCSRF = protectedAgainstCSRF;
	}


	public SessionGet getSessionGet() {
		return sessionGet;
	}


	public void setSessionGet(SessionGet sessionGet) {
		this.sessionGet = sessionGet;
	}


	public SessionSet getSessionSet() {
		return sessionSet;
	}


	public void setSessionSet(SessionSet sessionSet) {
		this.sessionSet = sessionSet;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public enum Method {
		POST, GET
	}

	public Method getMethod() {
		return method;
	}


	public void setMethod(Method method) {
		this.method = method;
	}


	public void hideSubmitButton() {
		this.showSubmitButton = false;
	}


	public boolean isShowSubmitButton() {
		return this.showSubmitButton;
	}
	
}
