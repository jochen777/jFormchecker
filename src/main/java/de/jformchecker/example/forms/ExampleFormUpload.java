package de.jformchecker.example.forms;

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
        .setRequired()
        .setDescription("Your fileupload"));
    
    this.disableHtml5Validation();

  }

 
}
