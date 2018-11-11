package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise01 {

	public static void main(String[] args) {
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH + "/employees.txt");
		List<Department> depList = Department.readFromUTF8TextFile(Contants.DATAPATH + "/departments.txt");

		// 排序
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});

		Map<Integer,List<Employee>> depIdEmpsmap = new HashMap<Integer, List<Employee>>();

		for (Employee emp : empList) {
			if (emp.getDepartmentId() != null) {// 如果雇员的部门id不为空，说明有部门
				List<Employee> emps = depIdEmpsmap.get(emp.getDepartmentId());
				if (emps == null) {
					emps = new ArrayList<Employee>();
					depIdEmpsmap.put(emp.getDepartmentId(), emps);
				}
				emps.add(emp);
			}
		}

		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			int rowNum = 0;
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("部门名称");
			row.createCell(1).setCellValue("雇员姓名");
			for (Department dep : depList) {
				if (depIdEmpsmap.containsKey(dep.getDepartmentId())) {
					List<Employee> emps = depIdEmpsmap.get(dep.getDepartmentId());
					for(int i=0;i<emps.size();i++) {
						Employee emp = emps.get(i);
						row = sheet.createRow(rowNum++);
						if(i==0)
							row.createCell(0).setCellValue(dep.getDepartmentName());
						row.createCell(1).setCellValue(emp.getName());
					}
				}
			}
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("总数:");
			row.createCell(1).setCellValue(empList.size());
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise01.class.getSimpleName() + ".xls"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wb != null)
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
