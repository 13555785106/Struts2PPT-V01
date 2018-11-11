package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hr.db.model.CellDataError;
import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class ExcelExam03 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH+"/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Department> depList = Department.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		// 按部门名称升序排序
		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		// 错误列表要清除上次的错误结果
		cdeList.clear();
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		// 按员工工资降序、姓名升序排序
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				double ret = o1.getSalary() - o2.getSalary();
				if (ret == 0) {
					return o1.getName().compareTo(o2.getName());
				} else if (ret > 0)
					return -1;
				else
					return 1;
			}
		});
		Set<Integer> distinctDepIdSet = new HashSet<>();
		Map<Integer, Set<Double>> depSalaryMapSet = new HashMap<>();
		for (Employee emp : empList) {
			distinctDepIdSet.add(emp.getDepartmentId());
			Set<Double> depSalarys = depSalaryMapSet.get(emp.getDepartmentId());
			if (depSalarys == null) {
				depSalarys = new HashSet<Double>();
				depSalaryMapSet.put(emp.getDepartmentId(), depSalarys);
			}
			depSalarys.add(emp.getSalary());
		}

		Map<Integer, Double> depThirdSalaryMap = new HashMap<>();
		for (Map.Entry<Integer, Set<Double>> entry : depSalaryMapSet.entrySet()) {
			List<Double> salaryList = new ArrayList<>(entry.getValue());
			Collections.sort(salaryList);
			Collections.reverse(salaryList);
			Double depThirdSalary = Double.MIN_VALUE;
			if (salaryList.size() >= 3)
				depThirdSalary = salaryList.get(2);
			depThirdSalaryMap.put(entry.getKey(), depThirdSalary);
		}
		// 将结果写到Excel文件
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			int rowNum = 0;// 行号
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("部门名称");
			row.createCell(1).setCellValue("员工姓名");
			row.createCell(2).setCellValue("员工工资");
			
			for (Department dep : depList) {
				if (distinctDepIdSet.contains(dep.getDepartmentId())) {
					boolean isFirstEmpOfDep = true;
					for (Employee emp : empList) {
						if (emp.getDepartmentId() == dep.getDepartmentId() && emp.getSalary()>=depThirdSalaryMap.get(dep.getDepartmentId())) {
							row = sheet.createRow(rowNum++);
							if(isFirstEmpOfDep) {
								row.createCell(0).setCellValue(dep.getDepartmentName());
								isFirstEmpOfDep = false;
							}
							row.createCell(1).setCellValue(emp.getName());
							row.createCell(2).setCellValue(emp.getSalary());		
						}
					}
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH+"/"+ExcelExam03.class.getSimpleName()+".xls"));
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
