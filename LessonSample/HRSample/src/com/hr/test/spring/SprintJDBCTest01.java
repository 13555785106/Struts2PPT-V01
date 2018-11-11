package com.hr.test.spring;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.hr.db.model.Employee;

public class SprintJDBCTest01 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/hr/test/spring/applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");
		
		List<Employee> empList = jdbcTemplate.query("SELECT * FROM employees WHERE salary>? AND salary<?",
				new Object[] {10000,15000},
				new BeanPropertyRowMapper<Employee>(Employee.class));
		for (Employee emp : empList)
			System.out.println(emp);
		
		List<Map<String,Object>> empMapList = jdbcTemplate.queryForList("SELECT * FROM employees WHERE salary>? AND salary<?",
				new Object[] {10000,15000});
		for (Map<String,Object> emp : empMapList)
			System.out.println(emp.get("employee_id"));
		
		long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM employees WHERE salary>? AND salary<?", new Object[] {10000,15000}, Long.class);
		System.out.println(count);
		
		Map<String,Object> emp = jdbcTemplate.queryForMap("SELECT * FROM employees WHERE employee_id=?", new Object[] {100});
		System.out.println(emp);
		
		int ret = jdbcTemplate.update("INSERT INTO departments (department_id,department_name) VALUES(departments_seq.nextval,?)", new Object[] {"BBBBB"});
		System.out.println(ret);
	}

}
