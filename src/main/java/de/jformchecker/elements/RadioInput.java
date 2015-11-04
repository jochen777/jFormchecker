package de.jformchecker.elements;

import java.util.LinkedHashMap;
import java.util.Map;

import de.jformchecker.FormCheckerElement;

public class RadioInput extends AbstractInput implements FormCheckerElement {

  LinkedHashMap<String, String> possibleNames = new LinkedHashMap<>(); // Linked Hashmap to maintain
                                                                       // sort order

  public static RadioInput build(String name) {
    RadioInput i = new RadioInput();
    i.name = name;
    return i;
  }


  public String getInputTag(Map<String, String> attributes) {
    StringBuffer inputTag = new StringBuffer();
    for (String key : possibleNames.keySet()) {
      // leer - bedeutet: Radio - Button ist optional, also nicht als radio ausgeben!
      if (!"".equals(possibleNames.get(key))) {
        inputTag.append(this.getInputTag(key, attributes) + " <label for=\"form_radio_"+key+"\" class=\"" + ""
            + "\" id=\"label_" + name + "_" + key + "\">" + possibleNames.get(key) + " </label>");
      }
    }
    return inputTag.toString();
  }

  public String getInputTag(String curValue, Map<String, String> attributes) {
    return "<input id=\"form_radio_" + curValue + "\" " + buildAttributes(attributes)+ " type=\"radio\" name=\"" + name + "\"  value=\"" + curValue + "\" "
        + getCheckedStatus(curValue) + "" + " " + " >";
  }

  private String getCheckedStatus(String _name) {
    if (value != null && value.equals(_name)) {
      return "checked";
    } else {
      return "";
    }
  }

  // RFE: We need a map here!
  public RadioInput setPossibleValues(LinkedHashMap<String, String> possibleNames) {
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
