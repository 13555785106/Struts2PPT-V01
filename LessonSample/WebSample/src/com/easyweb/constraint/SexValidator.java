package com.easyweb.constraint;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 性别校验器。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
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
