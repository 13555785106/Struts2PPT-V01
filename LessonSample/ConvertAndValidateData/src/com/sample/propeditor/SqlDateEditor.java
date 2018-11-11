package com.sample.propeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class SqlDateEditor extends PropertyEditorSupport {
	String pattern;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			date = new Date(sdf.parse(text).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		setValue(date);
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