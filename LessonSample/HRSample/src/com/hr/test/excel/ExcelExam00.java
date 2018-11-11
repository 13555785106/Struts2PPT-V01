package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.hr.db.model.Employee;
import com.hr.db.model.SalGrade;
import com.hr.test.Contants;

public class ExcelExam00 {
	public static void main(String[] args) {
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Employee> empList = Employee.readFromExcelFile("D:\\hrdata\\hr-exam.xls", cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		// 将结果写到Excel文件
		Workbook wb = null;
		try {
			int rowNum = 0;
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("姓名首字母");
			row.createCell(1).setCellValue("雇员姓名");
			row.createCell(2).setCellValue("入职日期");
			Calendar cal = Calendar.getInstance();
			char previousChar = 0;
			for (Employee emp : empList) {
				cal.setTime(emp.getHireDate());
				int curDay = cal.get(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);
				int monthLastDay = cal.get(Calendar.DAY_OF_MONTH);
				if (monthLastDay - curDay < 10) {
					row = sheet.createRow(rowNum++);
					char nameFirstChar = emp.getName().charAt(0);
					if (nameFirstChar != previousChar) {
						previousChar = nameFirstChar;
						row.createCell(0).setCellValue("" + previousChar);
					}
					row.createCell(1).setCellValue(emp.getName());
					row.createCell(2).setCellValue(sdf.format(emp.getHireDate()));
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + ExcelExam00.class.getSimpleName() + ".xls"));
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
