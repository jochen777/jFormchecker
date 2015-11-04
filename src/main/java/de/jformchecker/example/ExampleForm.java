package de.jformchecker.example;

import java.util.LinkedHashMap;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.ButtonInput;
import de.jformchecker.elements.CheckboxInput;
import de.jformchecker.elements.DateInput;
import de.jformchecker.elements.HTMLSnippet;
import de.jformchecker.elements.HiddenInput;
import de.jformchecker.elements.LongTextInput;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.elements.TextInput;

public class ExampleForm extends FormCheckerForm {

  public ExampleForm() {
    add(TextInput
        .build("firstname")
        .setDescription("Your Firstname")
        .setPreSetValue("Peter")
        .setCriterias(Criteria.accept("Peter", "Max"))
        );

    add(TextInput
        .build("lastname")
        .setDescription("Your Lastname")
        .setPreSetValue("pier")
        .setCriterias(Criteria.accept("Pan", "Mustermann"))
        );

    add(TextInput
        .build("middelname")
        .setDescription("Your Middelname")
        .setCriterias(new CustomValidation())
        );

    add(HTMLSnippet
        .build("headline")
        .setHTML("<h1>Headline</h1>")
        );

    add(HiddenInput
        .build("hidden")
        .setPreSetValue("something to remember")
        );

    add(ButtonInput
        .build("btn")
        .setDescription("Add")
        .setPreSetValue("add")
        );

    
    add(DateInput
        .build("date")
        .setDescription("Birthday")
        );



    add(PasswordInput
        .build("password")
        .setDescription("Password")
        );

    add(LongTextInput
        .build("description")
        .setRequired()
        .setDescription("Your Description")
        );

    // RFE: simple map-builder
    LinkedHashMap<String, String> radioEntries = createRadioMap();


    add(RadioInput
        .build("rdio")
        .setPossibleValues(radioEntries)
        .setDescription("Your Choice")
        );

    LinkedHashMap<String, String> selectEntries = createSelectMap();

    add(SelectInput
        .build("select")
        .setPossibleValues(selectEntries)
        .setDescription("Your Selection")
        );

    add(CheckboxInput
        .build("check")
        .setDescription("I order everything")
        );
    
    setPlaceholderMode();

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
