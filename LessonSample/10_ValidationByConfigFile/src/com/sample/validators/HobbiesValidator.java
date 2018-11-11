package com.sample.validators;

import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class HobbiesValidator extends FieldValidatorSupport {
	private String validHobbies = "足球,篮球,排球";

	public String getValidHobbies() {
		return validHobbies;
	}

	public void setValidHobbies(String validHobbies) {
		this.validHobbies = validHobbies;
	}

	@Override
	public void validate(Object object) throws ValidationException {
		List<String> hobbies = Arrays.asList(validHobbies.split(","));
		System.out.println("start---------------");
		System.out.println(object);
		String fieldName = getFieldName();
		System.out.println(fieldName);
		String hobbyStrs = (String) this.getFieldValue(fieldName, object);
		System.out.println(hobbyStrs);
		System.out.println("end---------------");
		if (hobbyStrs != null) {
			for (String hobby : hobbyStrs.split(",")) {
				if (!hobbies.contains(hobby))
					addFieldError(fieldName, object);
			}
		}
	}
}
