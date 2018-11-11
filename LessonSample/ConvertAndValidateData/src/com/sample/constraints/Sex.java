package com.sample.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = { SexValidator.class })
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sex {
	String message() default "性别必须是\"男\"或者\"女\"";
	String value () default "男";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
