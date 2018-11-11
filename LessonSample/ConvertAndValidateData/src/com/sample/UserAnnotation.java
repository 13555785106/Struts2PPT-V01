package com.sample;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
	public String account() default "sa";

	public String passwd() default "123";
	
	public String sex() default "男";

	public double salary() default 3000;

	public String birthday() default "2018-01-01";
	
	public String[] hobbies() default  {"足球"};
}
