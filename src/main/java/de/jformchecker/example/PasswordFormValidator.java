package de.jformchecker.example;

import org.apache.commons.lang3.StringUtils;

import de.jformchecker.FormCheckerElement;
import de.jformchecker.FormCheckerForm;
import de.jformchecker.FormValidator;

public class PasswordFormValidator implements FormValidator {

  @Override
  public void validate(FormCheckerForm form) {
    FormCheckerElement pwField1 = form.getElement("password1");
    FormCheckerElement pwField2 = form.getElement("password2");
    String pw1 = pwField1.getValue();
    String pw2 = pwField2.getValue();
    if (!StringUtils.equals(pw1, pw2)) {
      pwField1.setErrorMessage("Wrong pw");
      pwField1.setInvalid();
      pwField2.setErrorMessage("Wrong pw");
      pwField2.setInvalid();
    }
  }

}
