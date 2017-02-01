package de.jformchecker;

import java.util.Map;

import de.jformchecker.criteria.Criteria;
import de.jformchecker.criteria.MaxLength;
import de.jformchecker.message.MessageSource;
import de.jformchecker.message.MinimalMessageSource;
import de.jformchecker.request.Request;
import de.jformchecker.request.SessionGet;
import de.jformchecker.request.SessionSet;
import de.jformchecker.themes.BasicFormBuilder;
import de.jformchecker.validator.DefaultValidator;
import de.jformchecker.validator.Validator;

/**
 * FormChecker handles the initialisation, error- and submit status. This object
 * should be stored to be accessed from the template-system.
 */
public class FormChecker {
	Request req;
	SessionSet sessionSet;
	SessionGet sessionGet;
	boolean firstRun = true;
	boolean isValid = true;
	FormCheckerForm form = null;
	Validator validator = new DefaultValidator();

	boolean protectedAgainstCSRF = false; // TBD: Default no protection, because
											// the normal case is
											// not logged in?!?
	String completeForm;
	int defaultMaxLenElements = 1000; // override this for each element, if you
										// want longer vals!

	FormCheckerConfig config;

	String id;
	private String formAction = "#";

	// holds temporaryly config-infos while construction-process of fc
	private GenericFormBuilder tempFormBuilder;
	private MessageSource tempProperties;

	public static final String SUBMIT_KEY = "submitted";
	public static final String SUBMIT_VALUE_PREFIX = "FORMCHECKER_";

	public FormChecker(String _id, Request request) {
		id = _id;
		req = request;
	}

	public static FormChecker build(String _id, Request _req, FormCheckerForm form) {
		FormChecker fc = new FormChecker(_id, _req);
		fc.addForm(form);
		return fc;
	}

	public FormChecker setProtectAgainstCSRF(SessionGet sessionGet, SessionSet sessionSet) {
		protectedAgainstCSRF = true;
		this.sessionSet = sessionSet;
		this.sessionGet = sessionGet;
		return this;
	}

	public FormChecker setConfig(FormCheckerConfig config) {
		this.config = config;
		return this;
	}

	public boolean isValid() {
		return isValid;
	}

	public boolean isValidAndNotFirstRun() {
		return isValid && !firstRun;
	}

	public FormCheckerForm getForm() {
		return form;
	}

	public GenericFormBuilder getFormBuilder() {
		return config.getFormBuilder();
	}

	public FormChecker setFormBuilder(GenericFormBuilder formBuilder) {
		this.tempFormBuilder = formBuilder;
		return this;
	}

	public FormChecker setProperties(MessageSource properties) {
		this.tempProperties = properties;
		return this;
	}

	public static String getHelpBlockId(FormCheckerElement elem) {
		return "helpBlock_" + elem.getName();
	}

	public String getValue(String elementName) {
		return form.getElement(elementName).getValue();
	}

	public String getSubmitTag() {
		return config.getFormBuilder().getSubmittedTag(id);
	}

	public String getLabelTag(String elementName) {
		return config.getFormBuilder().getLabelForElement(form.getElement(elementName), new TagAttributes(), firstRun);
	}

	public String getLabelTag(String elementName, Map<String, String> map) {
		return config.getFormBuilder().getLabelForElement(form.getElement(elementName), new TagAttributes(map),
				firstRun);
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}

	private void prepareElement(FormCheckerElement element) {
		element.setFormChecker(this);

		// check, if maxLen is set. If not, add default-max-len
		// defaultMaxLenElements;
		boolean maxLenAvail = false;

		for (Criterion criterion : element.getCriteria()) {
			if (criterion instanceof MaxLength) {
				maxLenAvail = true;
			}
		}
		if (!maxLenAvail) {
			element.getCriteria().add(Criteria.maxLength(defaultMaxLenElements));
		}
	}

	public void addForm(FormCheckerForm form) {
		for (FormCheckerElement element : form.getElements()) {
			prepareElement(element);
		}
		this.form = form;
	}

	private String getGenericForm() {
		return config.getFormBuilder().generateGenericForm(id, formAction, form.elements, firstRun, this, req);
	}

	// TODO: is neeeded?
	public String getLabelForElement(FormCheckerElement e, Map<String, String> attribs) {
		return config.getFormBuilder().getLabelForElement(e, new TagAttributes(attribs), firstRun);
	}

	public FormChecker run() {

		setupConfig();

		sortTabIndexes();

		checkIfFirstRun();

		// process and validate each field
		for (FormCheckerElement elem : form.getElements()) {
			elem.init(req, firstRun, validator);
			if (!elem.isValid()) {
				isValid = false;
			}
		}

		// validate the complete form
		for (FormValidator formValidator : form.getValidators()) {
			formValidator.validate(form);
		}

		// build complete Form here!
		completeForm = this.getGenericForm();
		return this;
	}

	/**
	 * set up the config-object for formchecker (handles absence of properties
	 * and formBuider)
	 */
	private void setupConfig() {
		if (config == null) {
			if (tempFormBuilder == null) {
				tempFormBuilder = new BasicFormBuilder();
			}
			if (tempProperties == null) {
				tempProperties = new MinimalMessageSource();
			}
			config = new FormCheckerConfig(tempProperties, tempFormBuilder);
		}
	}

	// resort tab-indexes
	private void sortTabIndexes() {
		int tabIndex = 100;
		for (FormCheckerElement elem : form.getElements()) {
			elem.setTabIndex(tabIndex);
			tabIndex = elem.getLastTabIndex();
			tabIndex++;
		}
	}

	private void checkIfFirstRun() {
		if ((FormChecker.SUBMIT_VALUE_PREFIX + id).equals(req.getParameter(FormChecker.SUBMIT_KEY))) {
			firstRun = false;
		}
	}

	public String getCompleteForm() {
		return completeForm;
	}

	public FormCheckerConfig getConfig() {
		return config;
	}
}
