package de.jformchecker.example;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExampleBean {
  String firstname;
  String lastname;
  String description;

  
  
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
  
}
