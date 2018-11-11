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
import com.hr.db.model.Job;
import com.hr.test.Contants;
import com.hr.test.text.EmpTree;

public class Exercise12 {
	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();
		Collections.sort(empList, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		EmpTree empTree = new EmpTree(empList);
		empTree.walk();

		Workbook wb = null;
		try {
			int rowNum = 1;
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			int maxCol = 0;
			Row row = null;
			for (Employee emp : empTree.getResultEmpList()) {
				int col = empTree.getIdColMap().get(emp.getEmployeeId());
				if (col > maxCol)
					maxCol = col;
				row = sheet.createRow(rowNum++);
				row.createCell(col).setCellValue(emp.getEmployeeId()+":"+emp.getName()+":"+emp.getManagerId());
			}
			row = sheet.createRow(0);
			for(int i=0;i<=maxCol;i++) {
				row.createCell(i).setCellValue("第"+(i+1)+"层");
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise12.class.getSimpleName() + ".xls"));
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
