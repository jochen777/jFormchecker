package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.AttributeUtils;
import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;

public class ButtonInput extends AbstractInput<ButtonInput> implements FormCheckerElement {

	String buttonText = "";

	public static ButtonInput build(String name) {
		ButtonInput i = new ButtonInput();
		i.name = name;
		return i;
	}

	public ButtonInput setButtonText(String buttonText) {
		this.buttonText = buttonText;
		return this;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return "<button type=\"submit\" "+AttributeUtils.buildAttributes(tagAttributes)+" name=\"" + name + "\" value=\"" + getPreSetValue() + "\" " + getTabIndexTag()
				+ ">" + buttonText + "</button><br/>\n";
	}

	@Override
	public String getType() {
		return "button";
	}

}
