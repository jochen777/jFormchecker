package de.jformchecker.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.coverity.security.Escape;

import de.jformchecker.AttributeUtils;
import de.jformchecker.Criterion;
import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.TagAttributes;
import de.jformchecker.criteria.MaxLength;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.message.MessageSource;
import de.jformchecker.request.Request;
import de.jformchecker.validator.Validator;

/**
 * Parent Element for all Formchecker elements Common stuff like validation...
 * 
 * @author jochen
 *
 */
public abstract class AbstractInput <T extends FormCheckerElement> implements FormCheckerElement {

	protected String name;
	protected String value;
	protected String desc;
	protected String preSetValue = "";
	protected int size = -1;
	private List<Criterion> criteria = new ArrayList<>();
	boolean required;
	private int tabIndex;
	ValidationResult validationResult;
	protected TagAttributes inputAttributes;

	
	
	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationResult validationResult) {
		this.validationResult = validationResult;
		valid = validationResult.isValid();
	}

	boolean valid = true;
	FormChecker parent;
	String helpText;

	
	protected String buildAllAttributes(Map<String, String> attributes) {
		return this.buildAllAttributes(new TagAttributes(attributes), t -> "", false);
	}

	protected String buildAllAttributes(TagAttributes tagAttributes) {
		return this.buildAllAttributes(tagAttributes, t -> "", false);
	}
	
	// builds attribs, elementId, TabIndex
	protected String buildAllAttributes(TagAttributes tagAttributes, MessageSource messageSource, boolean html5Validation) {
		StringBuilder allAttribs = new StringBuilder();
		allAttribs.append(AttributeUtils.buildAttributes(tagAttributes));
		allAttribs.append(getElementId());
		allAttribs.append(getTabIndexTag());
		if (html5Validation) {
			allAttribs.append(buildRequiredAttribute());
			allAttribs.append(buildFcRequiredMessage(messageSource));
		}
		allAttribs.append(buildSizeAttribute());
		// help-text
		if (!StringUtils.isEmpty(helpText)) {
			allAttribs.append(
					AttributeUtils.buildAttributes(new TagAttributes("aria-describedby", 
							parent.getConfig().getFormBuilder().getHelpBlockId(this))));
		}
		return allAttribs.toString();
	}

	private String buildFcRequiredMessage(MessageSource messageSource) {
		String requiredMessage = messageSource.getMessage("jformchecker.required");
		if (!StringUtils.isEmpty(requiredMessage)) {
			return " data-errormessage-value-missing=\""+ requiredMessage +"\" ";
		}
		return "";
	}

	public void addCriteria(Criterion c) {
		criteria.add(c);
	}
	
	private Object buildSizeAttribute() {
		if (size != -1) {
			return AttributeUtils.buildSingleAttribute("size", Integer.toString(size));
		}
		return "";
	}

	protected String buildRequiredAttribute() {
		if (required) {
			return " required ";
		} else {
			return "";
		}
	}

	public String getInputTag() {
		return getInputTag(new HashMap<>(), t -> "", false);
	}

	public String getInputTag(Map<String, String> attributes, MessageSource messageSource, boolean html5Validation) {
		return getInputTag(attributes);
	}
	
	/**
	 * This is deprecated. Please use the getInputTag(attributes, messageSource) method
	 * 
	 */
	@Deprecated
	public String getInputTag(Map<String, String> attributes) {
		return "";
	}

	// return highest tabindex of this element
	public int getLastTabIndex() {
		return tabIndex;
	}

	@Override
	public void setFormChecker(FormChecker fc) {
		parent = fc;
	}

	public String getValueHtmlEncoded() {
		return Escape.html(value);
	}

	public void setInvalid() {
		valid = false;
	}

	@Override
	public void init(Request request, boolean firstRun, Validator validator) {
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			this.setValue(request.getParameter(this.getName()));
			this.setValidationResult(validator.validate(this));
		}
	}

	public T setRequired() {
		this.required = true;
		return (T) this;
	}

	@Override
	public String getLabel() {
		Map<String, String> map = new LinkedHashMap<>();
		return parent.getLabelForElement(this, map);
	}

	@Override
	public String getPreSetValue() {
		return preSetValue;
	}

	@Override
	public T setPreSetValue(String preSetValue) {
		this.preSetValue = preSetValue;
		this.value = preSetValue;
		return (T) this;
	}

	@Override
	public String getCompleteInput() {
		return getLabel() + getInputTag();
	}

	// builds the maxlen attribute
	public String buildMaxLen() {
		List<Criterion> criteria = this.getCriteria();
		if (criteria != null) {
			for (Criterion criterion : criteria) {
				if (criterion instanceof MaxLength) {
					return AttributeUtils.buildSingleAttribute("maxlength", Integer.toString(((MaxLength) criterion).getMaxLength()));
				}
			}
		}
		return "";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public T setDescription(String desc) {
		this.desc = desc;
		return (T) this;
	}

	@Override
	public void changeDescription(String desc) {
		this.desc = desc;
	}

	@Override
	public String getDescription() {
		return desc;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	public T setCriterias(Criterion... criteria) {
		this.criteria.addAll(Arrays.asList(criteria));
		return (T) this;
	}

	public boolean isRequired() {
		return required;
	}

	protected String getElementId() {
		return AttributeUtils.buildSingleAttribute("id", "form-" + name);
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public String getTabIndexTag() {
		return AttributeUtils.buildSingleAttribute("tabindex", Integer.toString(getTabIndex()));
	}

	public String getTabIndexTagIncreaseBy(int addition) {
		return AttributeUtils.buildSingleAttribute("tabindex", Integer.toString(getTabIndex() + addition));
	}

	public T setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
		return (T) this;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public String getHelpText() {
		return helpText;
	}

	public T setHelpText(String helpText) {
		this.helpText = helpText;
		return (T) this;
	}

	public T setSize(int size) {
		this.size = size;
		return (T) this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInputAttributes(TagAttributes inputAttributes) {
		this.inputAttributes = inputAttributes;
	}

	
}
