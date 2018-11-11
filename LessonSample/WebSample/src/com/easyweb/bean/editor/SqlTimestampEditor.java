package com.easyweb.bean.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
/**
 * java.sql.Timestamp与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlTimestampEditor extends PropertyEditorSupport {
	private static String pattern;
	private SimpleDateFormat sdf;

	public SqlTimestampEditor() {
		if (pattern == null)
			pattern = "yyyy-MM-dd HH:mm:ss.SSS";
		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Timestamp date = null;
		try {
			date = new Timestamp(sdf.parse(text).getTime());
			setValue(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		SqlTimestampEditor.pattern = pattern;
	}
}