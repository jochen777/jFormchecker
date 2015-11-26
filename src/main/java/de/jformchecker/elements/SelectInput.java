package de.jformchecker.elements;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class SelectInput extends AbstractInput implements FormCheckerElement {

  LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>(); // Linked Hashmap to maintain
                                                                       // sort order

  public static SelectInput build(String name) {
    SelectInput i = new SelectInput();
    i.name = name;
    return i;
  }


  public String getInputTag(Map<String, String> attributes) {

    String inputTag = "<select " + buildAllAttributes(attributes) + " name=\"" + name
        + "\" >\n";
    for (String key : possibleNames.keySet()) {
      String sel = "";
      if (value != null && value.equals(key)) {
        sel = " SELECTED ";
      }
      inputTag +=
          "<option value=\"" + key + "\"" + sel + ">" + possibleNames.get(key) + "</option>\n";
    }
    return inputTag + "</select>\n";
  }


  public SelectInput setPossibleValues(LinkedHashMap<String, String> possibleNames) {
    this.possibleNames = possibleNames;
    return this;
  }

  @Override
  public void setValue(String value) {
    if (possibleNames.containsKey(value)){
      this.value = value;
    }
  }

}
