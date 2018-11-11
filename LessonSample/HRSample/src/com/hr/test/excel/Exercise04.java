package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise04 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH+"/hr-exam.xls";
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		Map<Integer, List<Employee>> empManagerIdEmpsMap = new HashMap<Integer, List<Employee>>();
		for (Employee empManager : empList) {
			List<Employee> emps = empManagerIdEmpsMap.get(empManager.getEmployeeId());
			if (emps == null) {
				emps = new ArrayList<Employee>();
				empManagerIdEmpsMap.put(empManager.getEmployeeId(), emps);
			}
			for (Employee emp : empList) {
				if (empManager.getEmployeeId().equals(emp.getManagerId()))
					emps.add(emp);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("主管姓名");
			row.createCell(1).setCellValue("入职日期");
			row.createCell(2).setCellValue("雇员姓名");
			row.createCell(3).setCellValue("入职日期");
			int rowNum = 1;// 行号
			for (Employee empManager : empList) {
				List<Employee> emps = empManagerIdEmpsMap.get(empManager.getEmployeeId());
				if (emps.size() > 0) {
					boolean first = true;
					for (Employee emp : emps) {
						if (emp.getHireDate().before(empManager.getHireDate())) {
							row = sheet.createRow(rowNum);
							if (first) {
								first = false;
								row.createCell(0).setCellValue(empManager.getName());
								row.createCell(1).setCellValue(sdf.format(empManager.getHireDate()));
							}
							row.createCell(2).setCellValue(emp.getName());
							row.createCell(3).setCellValue(sdf.format(emp.getHireDate()));
							rowNum++;
						}
					}
				}
			}

			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise04.class.getSimpleName() + ".xls"));
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
