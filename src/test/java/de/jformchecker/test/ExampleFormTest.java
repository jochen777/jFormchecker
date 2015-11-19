package de.jformchecker.test;

import java.util.LinkedHashMap;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.CheckboxInput;
import de.jformchecker.elements.DateInput;
import de.jformchecker.elements.HiddenInput;
import de.jformchecker.elements.LongTextInput;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.elements.TextInput;
import de.jformchecker.example.CustomValidation;

public class ExampleFormTest extends FormCheckerForm {

  @Override
  public void init() {

    add(TextInput
        .build("firstname")
        .setDescription("Your Firstname")
        .setPreSetValue("Jochen")
        .setCriterias(Criteria.accept("Jochen", "Max")));

    add(TextInput
        .build("lasntame")
        .setDescription("Your Lastname")
        .setPreSetValue("pier")
        .setCriterias(Criteria.accept("Pan", "Mustermann"))
        );

    add(TextInput
        .build("middelname")
        .setDescription("Your Middelname")
        .setCriterias(new CustomValidation()));


    add(HiddenInput
        .build("hidden")
        .setPreSetValue("something to remember"));

    add(DateInput
        .build("date")
        .setDescription("Birthday"));



    add(PasswordInput
        .build("password")
        .setRequired()
        .setDescription("Password"));

    add(LongTextInput
        .build("description")
        .setRequired()
        .setDescription("Your Description"));

    LinkedHashMap<String, String> radioEntries = SampleMapGenerators.generateRadioMap();

    add(RadioInput
        .build("rdio")
        .setPossibleValues(radioEntries)
        .setDescription("Your Choice"));

    LinkedHashMap<String, String> selectEntries = SampleMapGenerators.generateSelectMap();

    add(SelectInput
        .build("select")
        .setPossibleValues(selectEntries)
        .setDescription("Your Selection"));

    add(CheckboxInput
        .build("check")
        .setDescription("I order everything"));

  }

 
}
