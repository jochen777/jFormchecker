package de.jformchecker.elements;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.TagAttributes;

public class SelectInput extends AbstractInput<SelectInput> implements FormCheckerElement {

	List<SelectInputEntry> entries = new ArrayList<>();
	List<SelectInputEntryGroup> groups = new ArrayList<>();
	

	public static SelectInput build(String name) {
		SelectInput i = new SelectInput();
		i.name = name;
		return i;
	}

	// a map is not a good structure for that, because keys may be used more than once
	@Deprecated
	public static SelectInput build(String name, LinkedHashMap<String, String> possibleNames) {
		SelectInput si = SelectInput.build(name);
		si.setPossibleValues(possibleNames);
		return si;
	}

	// convenience method
	public static SelectInput build(String name, String keys[], String values[]) {
		SelectInput si = SelectInput.build(name);
		if (keys.length != values.length) {
			throw new IllegalArgumentException("Key / Values with unequal length");
		}
		List<SelectInputEntry> entries = new ArrayList<>();
		for (int i = 0; i < keys.length; i++) {
			entries.add(si.generateEntry(keys[i], values[i]));
		}
		si.setEntries(entries);
		return si;
	}

	// build a select-box with groups (This may be impossible to build)
	public static SelectInput build(String name, List<SelectInputEntryGroup> inputGroups) {
		SelectInput si = SelectInput.build(name);
		si.setGroups(inputGroups);
		return si;
	}
	
	
	public String getInputTag(Map<String, String> attributes) {
		TagAttributes tagAttributes = new TagAttributes(attributes);
		tagAttributes.add(this.inputAttributes);
		StringBuilder inputTag = new StringBuilder("<select " + buildAllAttributes(tagAttributes) + " name=\"" + name + "\" >\n");
		if (groups.size() > 0) {
			groups.forEach(group ->
			{
				inputTag.append("<optgroup label=\""+group.getLabel()+"\">");
				buildEntries(inputTag, group.getEntries());
				inputTag.append("</optgroup>");
			}
			);
		} else {
			buildEntries(inputTag, entries);
		}
		return inputTag.append("</select>\n").toString();
	}

	private void buildEntries(StringBuilder inputTag, List<SelectInputEntry> entries2) {
		entries2.forEach(entry->{
			String sel = "";
			if (value != null && value.equals(entry.key)) {
				sel = " SELECTED ";
			}
			inputTag.append("<option value=\"" + entry.key + "\"" + sel + ">" + entry.value + "</option>\n");
		});
	}

	@Deprecated
	public SelectInput setPossibleValues(LinkedHashMap<String, String> possibleNames) {
		List<SelectInputEntry> entries = new ArrayList<>();
		possibleNames.forEach((k,v) ->
			entries.add(this.generateEntry(k, v))
				);
		this.entries = entries;
		return this;
	}
	
	public SelectInput setEntries(List<SelectInputEntry> entries) {
		this.entries = entries;
		return this;
	}

	public SelectInput setGroups(List<SelectInputEntryGroup> groups) {
		this.groups = groups;
		return this;
	}


	@Override
	public void setValue(String value) {
		if (groups.isEmpty()) {
			setValueForEntryList(entries, value);	
		} else {
			groups.forEach(group -> setValueForEntryList(group.getEntries(), value));
		}
		
	}

	private void setValueForEntryList(List<SelectInputEntry> entryList, String value) {
		for (SelectInputEntry entry : entryList) {
			if (value != null && value.equals(entry.getKey())){
				this.value = value;
				break;
			}
		}
	}
	
	@Override
	public String getType() {
		return "select";
	}

	public SelectInputEntry generateEntry(String key, String value) {
		return new SelectInputEntry(key, value);
	}
	
	// class that represents an entry in the selectInput
	public class SelectInputEntry {
		private final String key;
		private final String value;
		public SelectInputEntry(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public String getValue() {
			return value;
		}
	}
	
	// class that groups several inputEntries
	// See: https://www.w3schools.com/tags/tag_optgroup.asp
	public class SelectInputEntryGroup {
		private final String label;
		List<SelectInputEntry> entries;
		public SelectInputEntryGroup(String label, List<SelectInputEntry> entries) {
			this.label = label;
			this.entries = entries;
		}
		public SelectInputEntryGroup(String label) {
			this(label, new ArrayList<>());
		}
		public void addSelectInputEntry(SelectInputEntry entry) {
			entries.add(entry);
		}
		public String getLabel() {
			return label;
		}
		public List<SelectInputEntry> getEntries() {
			return entries;
		}
		
	}

	public SelectInputEntryGroup generateEntryGroup(String name) {
		return new SelectInputEntryGroup(name);
	}
	
}
