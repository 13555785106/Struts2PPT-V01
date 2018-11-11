package com.hr.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hr.db.Dac;

public class SpringTest02 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hr/test/spring/applicationContext.xml");
		/*Dac dac = (Dac)ac.getBean("dac");
		for(Employee emp:dac.getInstance().allEmployees())
			System.out.println(emp);*/
		/*Employee emp = new Employee();
		emp.setFirstName("aaaaa");
		emp.setLastName("dddd");
		emp.setEmail("VVVV");
		emp.setPhoneNumber("111111111");
		emp.setJobId("AD_VP");
		emp.setHireDate(new java.util.Date());
		emp.setSalary(9000);
		Dac.getInstance().addEmployee(emp);*/
		/*Employee emp = Dac.getInstance().getEmployee(207);
		emp.setFirstName("肖俊峰");
		Dac.getInstance().chgEmployee(emp);*/
		Dac.getInstance().delEmployee(207);
		//System.out.println(emp);
		/*for(Employee emp:Dac.getInstance().allEmployees())
			System.out.println(emp);*/
	}

}
