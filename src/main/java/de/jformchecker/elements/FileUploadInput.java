package de.jformchecker.elements;

import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;

public class FileUploadInput extends AbstractInput<FileUploadInput> implements FormCheckerElement {

	public static FileUploadInput build(String name) {
		FileUploadInput i = new FileUploadInput();
		i.name = name;
		return i;
	}

	@Override
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);

		return String.format(
				"<input " + buildAllAttributes(tagAttributes) + buildMaxLen() + "type=\"file\" name=\"%s\" value=\"%s\">",
				name, (value == null ? "" : getValueHtmlEncoded()));
	}
	
	@Override
	public String getType() {
		return "upload";
	}


}
