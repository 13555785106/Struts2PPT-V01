package com.hr.test.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hr.db.model.Employee;

public class EmpTree {
	private List<Employee> empList = null;//传入的雇员列表
	private List<Employee> resultEmpList = new ArrayList<>();//递归形成的雇员列表
	private Map<Integer, Integer> idColMap = new HashMap<>();// 每一个雇员的列表层级

	public EmpTree(List<Employee> empList) {
		setEmpList(empList);
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<Employee> getResultEmpList() {
		return resultEmpList;
	}

	public Map<Integer, Integer> getIdColMap() {
		return idColMap;
	}

	public void walk() {
		if (empList != null) {
			resultEmpList.clear();
			idColMap.clear();
			Employee rootEmp = null;
			for (Employee emp : empList) {
				if (emp.getManagerId() == null)
					rootEmp = emp;
			}
			if (rootEmp != null) {
				recursiveEmployee(rootEmp, 0);
			}
		}
	}

	private List<Employee> getEmpChilds(int parentId) {
		List<Employee> childrenList = new ArrayList<>();
		for (Employee emp : empList) {
			if (emp.getManagerId()!=null && emp.getManagerId() == parentId)
				childrenList.add(emp);
		}
		return childrenList;
	}

	private void recursiveEmployee(Employee emp, int colNum) {
		resultEmpList.add(emp);
		idColMap.put(emp.getEmployeeId(), colNum);
		List<Employee> children = getEmpChilds(emp.getEmployeeId());
		for (int i = 0; i < children.size(); i++) {
			Employee child = children.get(i);
			recursiveEmployee(child, colNum + 1);
		}
	}
}
