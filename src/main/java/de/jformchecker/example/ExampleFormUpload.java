package de.jformchecker.example;

import java.util.LinkedHashMap;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.FileUploadInput;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.TextInput;

public class ExampleFormUpload extends FormCheckerForm {

  public void init() {
    add(TextInput
        .build("firstname")
        .setDescription("Your Firstname")
        .setPreSetValue("Peter")
        .setRequired()
        .setCriterias(Criteria.accept("Peter", "Max"), Criteria.maxLength(10))
        );




    add(PasswordInput
        .build("password1")
        .setRequired()
        .setDescription("Password")
        );

    add(FileUploadInput
        .build("upload")
        .setDescription("Your fileupload"));
    
    this.disableHtml5Validation();

  }

  private LinkedHashMap<String, String> createSelectMap() {
    LinkedHashMap<String, String> selectEntries = new LinkedHashMap<>();
    selectEntries.put("green", "Green");
    selectEntries.put("blue", "Blue");
    selectEntries.put("yellow", "Yellow");
    return selectEntries;
  }

  private LinkedHashMap<String, String> createRadioMap() {
    LinkedHashMap<String, String> radioEntries = new LinkedHashMap<>();
    radioEntries.put("one", "One $");
    radioEntries.put("two", "Two $");
    radioEntries.put("three", "Three $");
    return radioEntries;
  }
}
