package com.sample.constraints;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexValidator implements ConstraintValidator<Sex, String> {
	private final String[] sexs = { "男", "女" };
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		if (Arrays.asList(sexs).contains(arg0))
			return true;
		else
			return false;
	}
}
