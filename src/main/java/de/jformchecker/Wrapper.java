package de.jformchecker;

// provides begin and end code, that can wrap something
public class Wrapper {
	public final String start;
	public final String end;

	public Wrapper(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

}
