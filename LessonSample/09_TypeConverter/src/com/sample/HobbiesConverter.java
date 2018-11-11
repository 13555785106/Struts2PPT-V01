package com.sample;

import java.util.Map;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class HobbiesConverter extends DefaultTypeConverter {

	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType) {
		System.out.println("---" + value);
		System.out.println("---" + toType);
		if (toType == String.class) {
			String[] strs = (String[]) value;
			if (strs.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String str : strs) {
					sb.append(str);
					sb.append(",");
				}
				return sb.substring(0, sb.length() - 1);
			}
		}
		throw new TypeConversionException();
	}
}
