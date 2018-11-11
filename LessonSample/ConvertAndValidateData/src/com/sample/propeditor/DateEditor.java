package com.sample.propeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			date = sdf.parse(text);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
		setValue(date);
	}

	@Override
	public String getAsText() {
		return sdf.format(super.getValue());
	}
}