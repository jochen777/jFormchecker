package de.jformchecker.criteria;

/**
 * A description of an object's invalid state.
 * 
 * @author armandino (at) gmail.com
 */
public interface MessageOnError {
  /**
   * Returns a message describing an error or an empty string if there was no error.
   */
  public String getOnError();

  /**
   * Sets a message to be displayed on error.
   */
  public void setOnError(String messageOnError);

}
