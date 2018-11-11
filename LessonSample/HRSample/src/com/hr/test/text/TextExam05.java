package com.hr.test.text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class TextExam05 {

	public static void main(String[] args) {
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH+"/employees.txt");
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e2.getName().compareTo(e2.getName());
			}
		});		
		StringBuilder sb = new StringBuilder();
		EmpTree empTree = new EmpTree(empList);
		empTree.walk();
		for(Employee emp : empTree.getResultEmpList()) {
			int col = empTree.getIdColMap().get(emp.getEmployeeId());
			for(int i=0;i<4*col;i++) {
				sb.append(' ');
			}
			sb.append(emp.getName());
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH+"/"+TextExam05.class.getSimpleName()+".txt") , "utf8"));
			br.write(sb.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
