package com.sample.propeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

public class SqlTimestampEditor extends PropertyEditorSupport {
	String pattern;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Timestamp timestamp = null;
		try {
			timestamp  = new Timestamp(sdf.parse(text).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		setValue(timestamp);
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