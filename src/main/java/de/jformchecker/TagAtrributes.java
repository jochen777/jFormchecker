package de.jformchecker;

import java.util.LinkedHashMap;
import java.util.Set;

// class that holds html tag-attributes
public class TagAtrributes {
  LinkedHashMap<String, String> attributes;

  public TagAtrributes(LinkedHashMap<String, String> attribs) {
    attributes = attribs;
  }
  
  public TagAtrributes() {
    this(new LinkedHashMap<>());
  }

  public TagAtrributes put(String key, String value) {
    attributes.put(key, value);
    return this;
  }

  public String get(String key) {
    return attributes.get(key);
  }

  public void addToAttribute(String key, String value) {
    if (!attributes.containsKey(key)) {
      attributes.put(key, value);
    } else {
      attributes.put(key, attributes.get(key) + value);
    }
  }

  public Set<String> keySet() {
    return attributes.keySet();
  }

}
