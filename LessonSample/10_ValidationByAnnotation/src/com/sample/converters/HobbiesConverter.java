package com.sample.converters;

import java.util.Map;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class HobbiesConverter extends DefaultTypeConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertValue(Map<String, Object> context, Object value, Class toType) {
		if (toType == String.class && value.getClass()==String[].class) {
			String[] strs = (String[]) value;
			if (strs.length > 0) {
				StringBuilder sb = new StringBuilder();
				for (String str : strs) {
					sb.append(str);
					sb.append(",");
				}
				return sb.substring(0, sb.length() - 1);
			}
		}else if(toType == String[].class && value.getClass()==String.class) {
			
		}
		throw new TypeConversionException();
	}
}
