package de.jformchecker.test.builders;

import java.util.LinkedHashMap;

public class SampleMapBuilders {
  public static LinkedHashMap<String, String> generateSelectMap() {
    LinkedHashMap<String, String> selectEntries = new LinkedHashMap<>();
    selectEntries.put("green", "Green");
    selectEntries.put("blue", "Blue");
    selectEntries.put("yellow", "Yellow");
    return selectEntries;
  }

  public static LinkedHashMap<String, String> generateRadioMap() {
    LinkedHashMap<String, String> radioEntries = new LinkedHashMap<>();
    radioEntries.put("one", "One $");
    radioEntries.put("two", "Two $");
    radioEntries.put("three", "Three $");
    return radioEntries;
  }
}
