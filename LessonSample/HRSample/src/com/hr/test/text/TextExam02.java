package com.hr.test.text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class TextExam02 {

	public static void main(String[] args) {
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH+"/employees.txt");
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				int e1DepId =0,e2DepId = 0;
				if (e1.getDepartmentId() == null)
					e1DepId = Integer.MAX_VALUE;
				if (e2.getDepartmentId() == null)
					e2DepId = Integer.MAX_VALUE;
			
				if (e1DepId - e2DepId == 0 )
					return e1.getName().compareTo(e2.getName());
				else
					return e1DepId - e2DepId;
			}
		});
		Map<Integer, List<Employee>> map = new HashMap<Integer, List<Employee>>();
		for (Employee emp : empList) {
			Integer departmentId = emp.getDepartmentId();
			List<Employee> list = map.get(departmentId);
			if (list == null) {
				list = new ArrayList<Employee>();
				map.put(departmentId, list);
			}
			list.add(emp);
		}
		List<Employee> depIdNullEmplist = map.get(null);
		map.remove(null);
		StringBuilder sb = new StringBuilder();
		List<Integer> depIds = new ArrayList<Integer>(map.keySet());
		Collections.sort(depIds);
		for (Integer depId : depIds) {
			sb.append("部门 id=" + depId);
			sb.append("\r\n");
			List<Employee> depEmpList = map.get(depId);
			for (Employee emp : depEmpList) {
				sb.append("    " + emp.getName());
				sb.append("\r\n");
			}
		}
		sb.append("部门 id=null");
		sb.append("\r\n");
		for (Employee emp : depIdNullEmplist) {
			sb.append("    " + emp.getName());
			sb.append("\r\n");
		}
		System.out.println(sb);
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH+"/"+TextExam02.class.getSimpleName()+".txt"), "utf8"));
			br.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
