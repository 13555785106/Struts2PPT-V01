package com.hr.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.telecom.geometry.Shape;

public class SpringTest01 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hr/test/spring/applicationContext.xml");
		Shape shape = (Shape)ac.getBean("shape");
		System.out.println(shape);	
	}

}
