package com.easyweb.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * java.util.Date与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class DateEditor extends PropertyEditorSupport {
	private static String pattern;
	private SimpleDateFormat sdf;

	public DateEditor() {
		if (pattern == null)
			pattern = "yyyy-MM-dd HH:mm:ss";
		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			date = sdf.parse(text);
			setValue(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		DateEditor.pattern = pattern;
	}
}