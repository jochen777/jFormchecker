package de.jformchecker.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import de.jformchecker.Criterion;
import de.jformchecker.FormChecker;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;
import de.jformchecker.Utils;
import de.jformchecker.criteria.MaxLength;
import de.jformchecker.criteria.ValidationResult;
import de.jformchecker.validator.Validator;

/**
 * Parent Element for all Formchecker elements Common stuff like validation...
 * 
 * @author jochen
 *
 */
public abstract class AbstractInput implements FormCheckerElement {

	protected String name;
	protected String value;
	protected String desc;
	protected String preSetValue = "";
	protected int size = -1;
	private List<Criterion> criteria = new ArrayList<>();
	boolean required;
	private int tabIndex;
	ValidationResult validationResult;

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	boolean valid = true;
	FormChecker parent;
	String helpText;

	// builds attribs, elementId, TabIndex
	protected String buildAllAttributes(Map<String, String> attributes) {
		StringBuilder allAttribs = new StringBuilder();
		allAttribs.append(Utils.buildAttributes(attributes));
		allAttribs.append(getElementId());
		allAttribs.append(getTabIndexTag());
		allAttribs.append(buildRequiredAttribute());
		allAttribs.append(buildSizeAttribute());
		// help-text
		if (!StringUtils.isEmpty(helpText)) {
			allAttribs.append(
					Utils.buildAttributes(new TagAttributes("aria-describedby", FormChecker.getHelpBlockId(this))));
		}
		return allAttribs.toString();
	}

	private Object buildSizeAttribute() {
		if (size != -1) {
			return Utils.buildSingleAttribute("size", Integer.toString(size));
		}
		return "";
	}

	protected String buildRequiredAttribute() {
		if (required) {
			return "required ";
		} else {
			return "";
		}
	}

	public String getInputTag() {
		return getInputTag(new HashMap<>());
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
		return StringEscapeUtils.escapeHtml4(value);
	}

	public void setInvalid() {
		valid = false;
	}

	@Override
	public void init(HttpServletRequest request, boolean firstRun, Validator validator) {
		if (firstRun) {
			this.setValue(this.getPreSetValue());
		} else {
			this.setValue(request.getParameter(this.getName()));
			this.setValidationResult(validator.validate(this));
			if (!this.validationResult.isValid()) {
				this.valid = false;
			} else {
				this.valid = true;
			}
		}
	}

	public AbstractInput setRequired() {
		this.required = true;
		return this;
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
	public AbstractInput setPreSetValue(String preSetValue) {
		this.preSetValue = preSetValue;
		this.value = preSetValue;
		return this;
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
					return Utils.buildSingleAttribute("maxlength", "" + ((MaxLength) criterion).getMaxLength());
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
	public AbstractInput setDescription(String desc) {
		this.desc = desc;
		return this;
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

	public AbstractInput setCriterias(Criterion... criteria) {
		this.criteria.addAll(Arrays.asList(criteria));
		return this;
	}

	public boolean isRequired() {
		return required;
	}

	protected String getElementId() {
		return Utils.buildSingleAttribute("id", "form_" + name);
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public String getTabIndexTag() {
		return Utils.buildSingleAttribute("tabindex", "" + getTabIndex());
	}

	public String getTabIndexTagIncreaseBy(int addition) {
		return Utils.buildSingleAttribute("tabindex", "" + (getTabIndex() + addition));
	}

	public AbstractInput setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
		return this;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	public String getHelpText() {
		return helpText;
	}

	public AbstractInput setHelpText(String helpText) {
		this.helpText = helpText;
		return this;
	}
	
	 public FormCheckerElement setSize(int size) {
		 this.size = size;
		 return this;
	 }
}
