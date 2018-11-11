package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
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

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise06 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		Calendar cal = Calendar.getInstance();
		Map<Integer, List<Employee>> yearEmpsMap = new HashMap<Integer, List<Employee>>();
		for (Employee emp : empList) {
			cal.setTime(emp.getHireDate());
			List<Employee> emps = yearEmpsMap.get(cal.get(Calendar.YEAR));
			if (emps == null) {
				emps = new ArrayList<Employee>();
				yearEmpsMap.put(cal.get(Calendar.YEAR), emps);
			}
			emps.add(emp);
		}

		int minCount = 0;
		int maxCount = 0;
		List<List<Employee>> empss = new ArrayList<List<Employee>>(yearEmpsMap.values());
		if (empss.size() > 0) {
			minCount = empss.get(0).size();
			maxCount = empss.get(0).size();
		}
		for (List<Employee> emps : empss) {
			if (emps.size() < minCount)
				minCount = emps.size();
			if (emps.size() > maxCount)
				maxCount = emps.size();
		}

		List<Integer> yearList = new ArrayList<Integer>(yearEmpsMap.keySet());
		Collections.sort(yearList);
		System.out.println(yearList);
		//DecimalFormat df = new DecimalFormat("#.00");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = null;
			int rowNum = 0;// 行号
			row= sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("入职最少年份");
			row.createCell(1).setCellValue("雇员姓名");
			for (Integer year : yearList) {
				List<Employee> emps = yearEmpsMap.get(year);
				if (minCount == emps.size()) {

					for(int i=0;i<emps.size();i++) {
						row = sheet.createRow(rowNum++);
						if(i==0) {
							row.createCell(0).setCellValue(year+"年 ("+emps.size()+"人)");
						}
						row.createCell(1).setCellValue(emps.get(i).getName());
					}
				}
			}
			rowNum++;
			row= sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("入职最多年份");
			row.createCell(1).setCellValue("雇员姓名");
			for (Integer year : yearList) {
				List<Employee> emps = yearEmpsMap.get(year);
				if (maxCount == emps.size()) {
					for(int i=0;i<emps.size();i++) {
						row = sheet.createRow(rowNum++);
						if(i==0) {
							row.createCell(0).setCellValue(year+"年  ("+emps.size()+"人)");
						}
						row.createCell(1).setCellValue(emps.get(i).getName());
					}
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise06.class.getSimpleName() + ".xls"));
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
