package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class FileUploadInput extends AbstractInput implements FormCheckerElement {

	public static FileUploadInput build(String name) {
		FileUploadInput i = new FileUploadInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		return String.format(
				"<input " + buildAllAttributes(attributes) + buildMaxLen() + "type=\"file\" name=\"%s\" value=\"%s\">",
				name, (value == null ? "" : getValueHtmlEncoded()));
	}

}
