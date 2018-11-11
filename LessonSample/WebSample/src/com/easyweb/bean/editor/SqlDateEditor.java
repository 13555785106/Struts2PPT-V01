package com.easyweb.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
/**
 * java.sql.Date与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlDateEditor extends PropertyEditorSupport {
	private static String pattern;
	private SimpleDateFormat sdf;

	public SqlDateEditor() {
		if (pattern == null)
			pattern = "yyyy-MM-dd";
		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date date = null;
		try {
			date = new Date(sdf.parse(text).getTime());
			setValue(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		SqlDateEditor.pattern = pattern;
	}
}