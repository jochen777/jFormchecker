package de.jformchecker.example;

import java.util.LinkedHashMap;

import de.jformchecker.FormCheckerForm;
import de.jformchecker.criteria.Criteria;
import de.jformchecker.elements.CheckboxInput;
import de.jformchecker.elements.DateInput;
import de.jformchecker.elements.Headline;
import de.jformchecker.elements.HiddenInput;
import de.jformchecker.elements.LongTextInput;
import de.jformchecker.elements.PasswordInput;
import de.jformchecker.elements.RadioInput;
import de.jformchecker.elements.SelectInput;
import de.jformchecker.elements.TextInput;

public class ExampleForm extends FormCheckerForm {

  public ExampleForm() {
    add(TextInput.build("firstname").setDescription("Vorname").setPreSetValue("Jochen")
        .setCriterias(Criteria.accept("Peter")));

    add(TextInput.build("lasntame").setDescription("Nachname:").setPreSetValue("pier")
        .setCriterias(Criteria.accept("Pan")));

    add(TextInput.build("middelname").setDescription("Name des Authors:")
        .setCriterias(new CustomValidation()));

    add(Headline.build("headline").setDescription("Zwischenüberschrift"));

    add(HiddenInput.build("hidden").setPreSetValue("Horst"));

    add(DateInput.build("date").setDescription("Geburtsdatum"));



    add(PasswordInput.build("password").setDescription("Ihr Passwort:"));

    add(LongTextInput.build("description").setPreSetValue("Ihre Beschreiubng").setRequired()
        .setDescription("Beschreibung:"));

    LinkedHashMap<String, String> radioEntries = new LinkedHashMap<>();
    radioEntries.put("one", "Eins");
    radioEntries.put("two", "Zwei");
    radioEntries.put("three", "Drei");

    // RFE: simple map-builder

    add(RadioInput.build("rdio").setPossibleValues(radioEntries).setDescription("Auswahl-Radio:"));

    LinkedHashMap<String, String> selectEntries = new LinkedHashMap<>();
    selectEntries.put("green", "Grün");
    selectEntries.put("blue", "Blau");
    selectEntries.put("yellow", "Gelb");

    // RFE: simple map-builder

    add(SelectInput.build("select").setPossibleValues(selectEntries)
        .setDescription("Ihre Auswahl Select:"));

    add(CheckboxInput.build("check").setDescription("Ich bestelle alles"));

  }
}
