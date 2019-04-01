package de.jformchecker;

/**
 * Some basic string utils to avoid dependance to commons
 * 
 * @author jochen
 *
 */
public class StringUtils {

  public static final String EMPTY_STR = "";

  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static String defaultString(final String str) {
    return alternative(str, EMPTY_STR);
  }

  /**
   * checks if the original string is empty. if empty/null return the alternative, otherwise the
   * original
   * 
   * @param original
   * @param alternative
   * @return
   */
  public static String alternative(final String original, final String alternative) {
    return isEmpty(original) ? alternative : original;
  }


}
