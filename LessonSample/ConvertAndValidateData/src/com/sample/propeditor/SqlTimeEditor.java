package com.sample.propeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Time;

public class SqlTimeEditor extends PropertyEditorSupport {
	String pattern;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Time time = null;
		try {
			time = new Time(sdf.parse(text).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		setValue(time);
	}

	@Override
	public String getAsText() {
		return sdf.format(getValue());
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}