package de.jformchecker.message;

/**
 * Source for mssages that should be displayed
 * typically coming from a property file holding localized messages 
 * @author jpier
 *
 */
public interface MessageSource {

  public String getMessage(String key);
  
  public default String getMessage(KeyAndReplacements kr) {
    return String.format(this.getMessage(kr.getKey()), 
        kr.getReplacements());
  }
  
}
