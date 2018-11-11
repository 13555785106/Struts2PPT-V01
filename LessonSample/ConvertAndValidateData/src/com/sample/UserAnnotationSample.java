package com.sample;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class UserAnnotationSample {
	@UserAnnotation(account = "sa", passwd = "123", salary = 9888, birthday = "2019-12-12") // 注解的使用
	private Object obj;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {

		Field ta = UserAnnotationSample.class.getDeclaredField("obj");
		UserAnnotation ua = ta.getAnnotation(UserAnnotation.class);// 得到注解,起到了标记的作用
		System.out.println(ua.account() + "," + ua.passwd() + "," + ua.sex() + "," + ua.salary() + "," + ua.birthday()
				+ "," + Arrays.asList(ua.hobbies()));

		for (Annotation annotation : ta.getDeclaredAnnotations()) {
			System.out.println(annotation);
		}

	}

}
