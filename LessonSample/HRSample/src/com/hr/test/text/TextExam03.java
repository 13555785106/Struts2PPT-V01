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

import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class TextExam03 {

	public static void main(String[] args) {
		List<Department> depList = Department.readFromUTF8TextFile(Contants.DATAPATH+"/departments.txt");
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH+"/employees.txt");
		//Map<雇员id, 雇员对象> 方便根据id获取对象
		Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();
		for (Employee emp : empList)
			empMap.put(emp.getEmployeeId(), emp);

		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
		//Map<部门id, 雇员列表> 方便根据部门id获取该部门的雇员列表
		Map<Integer, List<Employee>> depEmpsMap = new HashMap<Integer, List<Employee>>();
		for (Employee emp : empList) {
			Integer departmentId = emp.getDepartmentId();
			//获取部门id对应的雇员列表
			List<Employee> list = depEmpsMap.get(departmentId);
			if (list == null) {
				//如果为空，则创建一个空的雇员列表压进去
				list = new ArrayList<Employee>();
				depEmpsMap.put(departmentId, list);
			}
			list.add(emp);//将相应的雇员放到所在部门的列表中。
		}
		StringBuilder sb = new StringBuilder();
		for (Department dep : depList) {
			List<Employee> emps = depEmpsMap.get(dep.getDepartmentId());
			if (emps != null) {
				sb.append("部门名称：").append(dep.getDepartmentName()).append(" 部门主管：")
						.append(empMap.get(dep.getManagerId()).getName()).append(" 共").append(emps.size())
						.append("人\r\n");
				for (Employee emp : emps) {
					sb.append("    ").append(emp.getName()).append("\r\n");
				}
			}
		}
		for (Department dep : depList) {
			List<Employee> emps = depEmpsMap.get(dep.getDepartmentId());
			if (emps == null) {
				sb.append(dep.getDepartmentName()).append(" 无人\r\n");

			}
		}
		System.out.println(sb);
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH+"/"+TextExam03.class.getSimpleName()+".txt"), "utf8"));
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
