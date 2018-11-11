package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise07 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		Workbook wb = null;
		try {
			int rowNum = 0;// 行号
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("雇员姓名");
			row.createCell(1).setCellValue("电话号码");

			for (Employee emp : empList) {
				//System.out.println(emp.getPhoneNumber());
				//System.out.println(emp.getPhoneNumber().matches(".*([\\d])\\1{2}.*"));
				if (emp.getPhoneNumber().matches(".*([\\d])\\1{3,}.*")) {
					row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(emp.getName());
					row.createCell(1).setCellValue(emp.getPhoneNumber());
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise07.class.getSimpleName() + ".xls"));
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
