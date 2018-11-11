package com.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class ChineseDateConverter extends StrutsTypeConverter {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		System.out.println("ChineseDateConverter-convertFromString:" + context);
		for (Object o : context.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		if (values != null && values.length >= 1) {
			java.util.Date date = null;
			try {
				date = sdf.parse(values[0]);
			} catch (ParseException e) {
				throw new TypeConversionException();
			}
			if (date != null)
				return new java.sql.Date(date.getTime());
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		// System.out.println("ChineseDateConverter-convertToString:" + context);
		if (o instanceof java.sql.Date) {
			return sdf.format((java.sql.Date) o);
		}
		return null;
	}

}
