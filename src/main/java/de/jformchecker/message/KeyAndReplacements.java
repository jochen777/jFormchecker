package de.jformchecker.message;

/**
 * holds the message key and possible replacements. used by criterias, that
 * return an error message and objejcts, that should be translated.
 * 
 * @author jpier
 *
 */
public class KeyAndReplacements {
	String key;
	Object[] replacements;

	public KeyAndReplacements(String key, Object[] replacements) {
		super();
		this.key = key;
		this.replacements = replacements;
	}

	public String getKey() {
		return key;
	}

	public Object[] getReplacements() {
		return replacements;
	}

}
