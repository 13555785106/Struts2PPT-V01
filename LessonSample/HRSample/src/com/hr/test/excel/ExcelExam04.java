package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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

import com.hr.db.model.CellDataError;
import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class ExcelExam04 {
	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<CellDataError>();

		List<Department> depList = Department.readFromExcelFile(xlsFileName, cdeList);
		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		for (Department dep : depList)
			System.out.println(dep);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName, cdeList);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
		for (Employee emp : empList)
			System.out.println(emp);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();

		Map<Integer, List<Employee>> depEmpsMap = new HashMap<Integer, List<Employee>>();
		for (Employee emp : empList) {
			if (emp.getDepartmentId() != null) {
				List<Employee> emps = depEmpsMap.get(emp.getDepartmentId());
				if (emps == null) {
					emps = new ArrayList<Employee>();
					depEmpsMap.put(emp.getDepartmentId(), emps);
				}
				emps.add(emp);
			}
		}

		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("单位名称");
			row.createCell(1).setCellValue("最高工资");
			row.createCell(2).setCellValue("雇员姓名");
			row.createCell(3).setCellValue("最低工资");
			row.createCell(4).setCellValue("雇员姓名");
			int rowNum = 1;
			Map<Integer, Double> depAvgSalaryMap = new HashMap<Integer, Double>();
			List<Department> depHaveEmps = new ArrayList<Department>();
			for (Department dep : depList) {
				if (depEmpsMap.keySet().contains(dep.getDepartmentId())) {
					depHaveEmps.add(dep);
					List<Employee> emps = depEmpsMap.get(dep.getDepartmentId());
					double sumSalary = 0;
					for (Employee emp : emps) {
						sumSalary += emp.getSalary();
					}
					depAvgSalaryMap.put(dep.getDepartmentId(), sumSalary / emps.size());

					Comparator<Employee> salaryComp = new Comparator<Employee>() {
						@Override
						public int compare(Employee o1, Employee o2) {
							double ret = o1.getSalary() - o2.getSalary();
							if (ret > 0)
								return 1;
							else if (ret < 0)
								return -1;
							else
								return 0;
						}

					};
					double maxSalary = Collections.max(emps, salaryComp).getSalary();
					double minSalary = Collections.min(emps, salaryComp).getSalary();
					String maxSalNames = "";
					String minSalNames = "";
					for (Employee emp : emps) {
						if (emp.getSalary() == maxSalary)
							maxSalNames += emp.getName() + ",";
						if (emp.getSalary() == minSalary)
							minSalNames += emp.getName() + ",";
					}
					if (maxSalNames.endsWith(","))
						maxSalNames = maxSalNames.substring(0, maxSalNames.length() - 1);
					if (minSalNames.endsWith(","))
						minSalNames = minSalNames.substring(0, minSalNames.length() - 1);
					row = sheet.createRow(rowNum);
					row.createCell(0).setCellValue(dep.getDepartmentName());
					row.createCell(1).setCellValue(maxSalary);
					row.createCell(2).setCellValue(maxSalNames);
					row.createCell(3).setCellValue(minSalary);
					row.createCell(4).setCellValue(minSalNames);
					rowNum++;
				}
			}

			Collections.sort(depHaveEmps, new Comparator<Department>() {
				@Override
				public int compare(Department o1, Department o2) {
					double ret = depAvgSalaryMap.get(o1.getDepartmentId()) - depAvgSalaryMap.get(o2.getDepartmentId());
					if (ret > 0)
						return 1;
					else if (ret < 0)
						return -1;
					else
						return 0;
				}
			});
			rowNum++;
			rowNum++;
			DecimalFormat df = new DecimalFormat("#.00");
			row = sheet.createRow(rowNum);
			if(depHaveEmps.size()>0) {
				row.createCell(0).setCellValue("平均工资最高部门");
				row.createCell(1).setCellValue(depHaveEmps.get(depHaveEmps.size()-1).getDepartmentName());
				row.createCell(2).setCellValue(df.format(depAvgSalaryMap.get(depHaveEmps.get(depHaveEmps.size()-1).getDepartmentId())));
			}
			rowNum++;
			row = sheet.createRow(rowNum);
			if(depHaveEmps.size()>1) {
				row.createCell(0).setCellValue("平均工资最低部门");
				row.createCell(1).setCellValue(depHaveEmps.get(0).getDepartmentName());
				row.createCell(2).setCellValue(df.format(depAvgSalaryMap.get(depHaveEmps.get(0).getDepartmentId())));
			}
			
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + ExcelExam04.class.getSimpleName() + ".xls"));
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
