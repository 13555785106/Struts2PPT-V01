package com.hr.test.text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hr.db.model.Employee;
import com.hr.test.Contants;
import com.hr.util.StringUtils;

public class TextExam01 {

	public static void main(String[] args) {
		List<Employee> empListAll = Employee.readFromUTF8TextFile(Contants.DATAPATH+"/employees.txt");
		Collections.sort(empListAll, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				double ret = e1.getSalary() - e2.getSalary();
				if (ret == 0) {
					ret = e1.getFirstName().compareTo(e2.getFirstName());
					if (ret == 0) {
						return e1.getLastName().compareTo(e2.getLastName());
					} else
						return (int) ret;
				} else if (ret > 0)
					return -1;
				else
					return 1;
			}
		});

		List<Employee> empList = new ArrayList<Employee>();
		for (Employee emp : empListAll) {
			if(emp.getSalary()>=7000)
				empList.add(emp);
		}

		String[] titles = {"FirstName","LastName","Salary"};
		int[] colLengths = new int[3];
		for(int i=0;i<titles.length;i++)
			colLengths[i] = titles[i].length();
		
		DecimalFormat df = new DecimalFormat("#.00");
		StringBuilder sb = new StringBuilder();
		
		for(Employee emp : empList) {
			if(emp.getFirstName().length()>colLengths[0])
				colLengths[0]=emp.getFirstName().length();
			if(emp.getLastName().length()>colLengths[1])
				colLengths[1]=emp.getLastName().length();
			String salaryStr = df.format(emp.getSalary());
			if(salaryStr.length()>colLengths[2])
				colLengths[2]=salaryStr.length();
		}

		for(int i=0;i<titles.length;i++) {
			sb.append(StringUtils.rpad(titles[i], ' ', colLengths[i]+1));	
		}
		sb.append("\r\n");
		for(Employee emp : empList) {
			sb.append(StringUtils.rpad(emp.getFirstName(), ' ', colLengths[0]+1));
			sb.append(StringUtils.rpad(emp.getLastName(), ' ', colLengths[1]+1));
			sb.append(StringUtils.lpad(df.format(emp.getSalary()), ' ', colLengths[2]));
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH+"/"+TextExam01.class.getSimpleName()+".txt") , "utf8"));
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
