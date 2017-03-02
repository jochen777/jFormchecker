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
	boolean firstRun = true;
	boolean isValid = true;
	FormCheckerForm form = null;
	Validator validator = new DefaultValidator();

	@Deprecated
	String id;


	/** Maximal lenght of input vars to avoid too long requests  
	 * If you want to get longer element-vals, override it for each element!
	 */
	int defaultMaxLenElements = 1000;

	FormCheckerConfig config;

	String formAction = "#";

	// holds temporaryly config-infos while construction-process of fc
	private GenericFormBuilder tempFormBuilder;
	private MessageSource tempProperties;

	public static final String SUBMIT_KEY = "submitted";
	public static final String SUBMIT_VALUE_PREFIX = "FORMCHECKER_";

	/**
	 * Deprecated: Put id into the FormcheckerForm and use build(request, form) or
	 * Formchecker(request)
	 * 
	 * @param _id
	 * @param request
	 */
	@Deprecated
	public FormChecker(String _id, Request request) {
		id = _id;
		req = request;
	}

	public FormChecker(Request request) {
		req = request;
	}

	/**
	 * Deprecated: Use build(Request _req, FormCheckerForm form) and put id into the FormCheckerForm
	 * @param _id
	 * @param _req
	 * @param form
	 * @return
	 */
	@Deprecated
	public static FormChecker build(String _id, Request _req, FormCheckerForm form) {
		FormChecker fc = new FormChecker(_id, _req);
		fc.id = _id;
		fc.addForm(form);
		return fc;
	}

	public static FormChecker build(Request _req, FormCheckerForm form) {
		FormChecker fc = new FormChecker(_req);
		fc.addForm(form);
		return fc;
	}

	public FormChecker setProtectAgainstCSRF(SessionGet sessionGet, SessionSet sessionSet) {
		if (form == null) {
			throw new IllegalArgumentException("Set the FormcheckerForm BEFORE calling setAgainstCSRF");
		}
		form.setProtectedAgainstCSRF(true);
		form.setSessionGet(sessionGet);
		form.setSessionSet(sessionSet);
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

	/**
	 *  moved to FormBuilder. please use:
	 *  formchecker.getConfig().getFormBuilder().getHelpBlockId(elem)...
	 *  
	 * @param elem
	 * @return
	 */
	@Deprecated
	public static String getHelpBlockId(FormCheckerElement elem) {
		return "helpBlock_" + elem.getName();
	}

	public String getValue(String elementName) {
		return form.getElement(elementName).getValue();
	}

	@Deprecated
	public String getSubmitTag() {
		return this.getView().getSubmit();
	}

	/**
	 * use fc.getView().getLabelHtml(elementName) instead
	 * @param elementName
	 * @return
	 */
	@Deprecated
	public String getLabelTag(String elementName) {
		return this.getView().getLabel(elementName);
	}

	/**
	 * use fc.getView().getLabelTag(elementName, map); instead
	 * @param elementName
	 * @return
	 */
	@Deprecated
	public String getLabelTag(String elementName, Map<String, String> map) {
		return this.getView().getLabel(elementName, map);
	}
	
	/**
	 * Get the view for displaying html in the template
	 * @return
	 */
	public View getView() {
		return new View(form, this.getFormBuilder(), this);
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
		this.form = form;
		if (id != null) {	// Internal id overrides id from form
			form.setId(id);
		}
	}

	
	private String getGenericForm() {
		return config.getFormBuilder().generateGenericForm(formAction, firstRun, form, req, config);
	}

	// move to ViewFacade
	public String getLabelForElement(FormCheckerElement e, Map<String, String> attribs) {
		return config.getFormBuilder().getLabelForElement(e, new TagAttributes(attribs), firstRun);
	}

	public FormChecker run() {

		setupConfig();

		sortTabIndexes();

		initForm();

		checkIfFirstRun();

		processAndValidateElements();

		validateCompleteForm();

		return this;
	}

	private void validateCompleteForm() {
		for (FormValidator formValidator : form.getValidators()) {
			if (formValidator.validate(form) == false) {
				isValid = false;
				// continue loop here, because other FormValidators can set manually errors on vields
			}
		}
	}

	private void processAndValidateElements() {
		for (FormCheckerElement elem : form.getElements()) {
			elem.init(req, firstRun, validator);
			if (!elem.isValid()) {
				isValid = false;
			}
		}
	}

	private void initForm() {
		form.setMessageSource(this.getConfig().getProperties());
		form.init();

		for (FormCheckerElement element : form.getElements()) {
			prepareElement(element);
		}

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
		if ((FormChecker.SUBMIT_VALUE_PREFIX + form.getId()).equals(req.getParameter(FormChecker.SUBMIT_KEY))) {
			firstRun = false;
		}
	}

	// move to ViewFacade
	public String getCompleteForm() {
		return this.getGenericForm();
	}

	public FormCheckerConfig getConfig() {
		return config;
	}
}
