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

import com.hr.db.model.CellDataError;
import com.hr.db.model.Employee;
import com.hr.db.model.SalGrade;
import com.hr.test.Contants;

public class ExcelExam06 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<SalGrade> sgList = SalGrade.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		// 错误列表要清除上次的错误结果
		cdeList.clear();
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		// 按员工工资降序、姓名升序排序
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				double ret = o2.getSalary() - o1.getSalary();
				if (ret > 0)
					return 1;
				else if (ret < 01)
					return -1;
				else
					return o1.getName().compareTo(o2.getName());
			}
		});
		// 声明一个工资级别名称为主键、对应雇员列表为值的Map。
		Map<String, List<Employee>> sgEmpMap = new HashMap<>();
		// 遍历员工列表，将对应的雇员添加到对应级别的List
		for (Employee emp : empList) {
			// 获取工资级别
			String gradeLevel = null;
			for (SalGrade sg : sgList) {
				if (emp.getSalary() >= sg.getLowestSalary() && emp.getSalary() < sg.getHighestSalary()) {
					gradeLevel = sg.getGradeLevel();
				}
			}
			if (gradeLevel != null) {
				List<Employee> emps = sgEmpMap.get(gradeLevel);
				if (emps == null) {
					emps = new ArrayList<Employee>();
					sgEmpMap.put(gradeLevel, emps);
				}
				emps.add(emp);
			}
		}

		List<String> sgKeys = new ArrayList<String>(sgEmpMap.keySet());
		Collections.sort(sgKeys);// 排序工资级别的名称
		Collections.reverse(sgKeys);// 翻转后倒序
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			int rowNum = 0;// 行号
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("工资级别");
			row.createCell(1).setCellValue("雇员姓名");
			row.createCell(2).setCellValue("雇员工资");

			for (String gradeLevel : sgKeys) {
				List<Employee> emps = sgEmpMap.get(gradeLevel);
				for (int i = 0; i < emps.size(); i++) {
					Employee emp = emps.get(i);
					row = sheet.createRow(rowNum++);
					if (i == 0)
						row.createCell(0).setCellValue(gradeLevel);
					row.createCell(1).setCellValue(emp.getName());
					row.createCell(2).setCellValue(emp.getSalary());
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + ExcelExam06.class.getSimpleName() + ".xls"));
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
