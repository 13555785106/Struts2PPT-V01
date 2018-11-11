package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise08 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		Map<String,Integer> nameCountMap = new HashMap<String,Integer>();
		for(Employee emp : empList) {
			Integer count = nameCountMap.get(emp.getFirstName());
			if(count == null)
				count =0;
			nameCountMap.put(emp.getFirstName(), ++count);
		}
		List<Map.Entry<String, Integer>> ncList = new ArrayList<>(nameCountMap.entrySet());
		Collections.sort(ncList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				int ret= o2.getValue()-o1.getValue();
				if(ret==0)
					return o1.getKey().compareTo(o2.getKey());
				else
					return ret;
			}
		});
		
		
		
		Workbook wb = null;
		try {
			int rowNum = 0;// 行号
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("名字(first_name)");
			row.createCell(1).setCellValue("人数");
			for(Map.Entry<String, Integer> entry : ncList) {
				row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(entry.getKey());
				row.createCell(1).setCellValue(entry.getValue());
			}
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("名字数量:");
			row.createCell(1).setCellValue(ncList.size());
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise08.class.getSimpleName() + ".xls"));
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
