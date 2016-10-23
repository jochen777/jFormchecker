package de.jformchecker.message;

import de.jformchecker.criteria.ValidationResult;

/**
 * Source for mssages that should be displayed typically coming from a property
 * file holding localized messages
 * 
 * @author jpier
 *
 */
@FunctionalInterface
public interface MessageSource {

	public String getMessage(String key);

	public default String getMessage(ValidationResult vr) {
		if (vr.getMessage() != null) {
			return(String.format(this.getMessage(
					vr.getMessage()
					), vr.getErrorVals()));
			
		} else {
			return vr.getTranslatedMessage();
		}
	}

}
