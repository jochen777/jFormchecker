package de.jformchecker.message;

public class MinimalMessageSource implements MessageSource {

	@Override
	public String getMessage(String key) {
		return key;
	}

}
