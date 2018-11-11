package com.easyweb.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Time;
/**
 * java.sql.Time与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlTimeEditor extends PropertyEditorSupport {
	private static String pattern;
	private SimpleDateFormat sdf;

	public SqlTimeEditor() {
		if (pattern == null)
			pattern = "HH:mm:ss";
		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Time date = null;
		try {
			date = new Time(sdf.parse(text).getTime());
			setValue(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		SqlTimeEditor.pattern = pattern;
	}
}