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
import com.hr.db.model.Employee;
import com.hr.db.model.SalGrade;
import com.hr.test.Contants;

public class ExcelExam01 {
	public static void main(String[] args) {
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<SalGrade> sgList = SalGrade.readFromExcelFile(Contants.DATAPATH + "/hr-exam.xls", cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		// 按级别名称升序排序
		Collections.sort(sgList, new Comparator<SalGrade>() {
			@Override
			public int compare(SalGrade o1, SalGrade o2) {
				return o1.getGradeLevel().compareTo(o2.getGradeLevel());
			}
		});
		// 错误列表要清除上次的错误结果
		cdeList.clear();
		List<Employee> empList = Employee.readFromExcelFile("D:\\hrdata\\hr-exam.xls", cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		Collections.sort(empList,new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				double ret = o1.getSalary() - o2.getSalary();
				if (ret > 0)
					return 1;
				else if (ret < 0)
					return -1;
				else
					return o1.getName().compareTo(o2.getName());
			}

		});
		
		
		Map<String, List<Employee>> sgEmpsMap = new HashMap<String, List<Employee>>();

		// 遍历员工列表，判断其在哪个级别，并将相应级别对应的人数加1
		for (Employee emp : empList) {
			for (SalGrade sg : sgList) {
				double salary = emp.getSalary();
				if (salary >= sg.getLowestSalary() && salary < sg.getHighestSalary()) {
					String gl = sg.getGradeLevel();
					List<Employee> emps = sgEmpsMap.get(gl);
					if (emps == null) {
						emps = new ArrayList<Employee>();
						sgEmpsMap.put(gl, emps);
					}
					emps.add(emp);
				}
			}
		}
		DecimalFormat df = new DecimalFormat("#");
		// 将结果写到Excel文件
		Workbook wb = null;
		try {
			int rowNum = 0;
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("工资级别");
			row.createCell(1).setCellValue("人数");
			for (SalGrade sg : sgList) {
				List<Employee> emps = sgEmpsMap.get(sg.getGradeLevel());
				if (emps != null) {
					row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(sg.getGradeLevel()+"("+df.format(sg.getLowestSalary())+" - "+df.format(sg.getHighestSalary())+")");
					row.createCell(1).setCellValue(emps.size());
					for(int i=0;i<emps.size();i++) {
						Employee emp = emps.get(i);
						row.createCell(2+i).setCellValue(emp.getName()+"-"+df.format(emp.getSalary()));
					}
				}
			}
			rowNum++;
			row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue("人员总数：");
			row.createCell(1).setCellValue(empList.size());
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + ExcelExam01.class.getSimpleName() + ".xls"));
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
