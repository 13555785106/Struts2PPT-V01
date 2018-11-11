package com.sample.constraints;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbiesValidator implements ConstraintValidator<Hobbies, String[]> {
	private final List<String> hobbies = Arrays.asList(new String[] { "足球", "篮球", "排球" });

	@Override
	public boolean isValid(String[] arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null)
			return false;
		for (String hobby : arg0) {
			if (!hobbies.contains(hobby))
				return false;
		}
		return true;
	}
}
