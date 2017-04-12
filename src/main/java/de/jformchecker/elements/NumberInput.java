package de.jformchecker.elements;

import java.util.Map;

import com.coverity.security.Escape;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.StringUtils;
import de.jformchecker.criteria.Criteria;

/**
 * Input Element that handles numbers (starting with int)
 * @author jochen
 *
 */
public class NumberInput extends AbstractInput<NumberInput> implements FormCheckerElement {

	private String placeholderText = "";

	public static NumberInput build(String name) {
		NumberInput i = new NumberInput();
		i.addCriteria(Criteria.number());
		i.name = name;
		return i;
	}

	public NumberInput setPlaceholerText(String placeholderText) {
		this.placeholderText = placeholderText;
		return this;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return String.format("<input " + buildAllAttributes(attributes) + buildMaxLen()
				+ "type=\"number\" name=\"%s\" value=\"%s\"" + getPlaceholder() + ">", name,
				(value == null ? "" : getValueHtmlEncoded()));
	}

	private String getPlaceholder() {
		return StringUtils.isEmpty(placeholderText) ? ""
				: " placeholder=\"" + Escape.htmlText(placeholderText) + "\"";
	}
	
	public void setValue(int value) {
		this.setValue(Integer.toString(value));
	}
	
	public int getIntValue() {
		return Integer.parseInt(this.getValue());
	}

	
	public FormCheckerElement presetIntValue(int intVal) {
		this.setPreSetValue(Integer.toString(intVal));
		return this;
	}
	
	@Override
	public String getType() {
		return "number";
	}


}
