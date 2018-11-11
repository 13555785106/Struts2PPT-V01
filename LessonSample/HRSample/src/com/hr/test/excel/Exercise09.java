package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise09 {

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
		// 按员工姓名升序排序
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		// 声明一个部门主键与部门对象的Map，方便使用主键获取部门对象
		Map<Integer, Department> depMap = new HashMap<Integer, Department>();
		for (Department dep : depList) {
			depMap.put(dep.getDepartmentId(), dep);
		}
		/*
		 * 声明一个部门与员工列表的Map,使用Dep做主键，因此， Dep类要重写hashCode与equals方法
		 */
		Map<Department, List<Employee>> depEmpsMap = new HashMap<Department, List<Employee>>();

		// 遍历员工列表
		for (Employee emp : empList) {
			// 通过员工的部门主键得到部门对象
			Department dep = depMap.get(emp.getDepartmentId());
			if (dep != null) {// 有员工无部门，所以判断是否为空
				// 使用部门对象做主键获取部门的员工列表
				List<Employee> emps = depEmpsMap.get(dep);
				// 如果员工部门列表为null，说明员工列表不存在，放一个空列表进去
				if (emps == null) {
					emps = new ArrayList<Employee>();
					depEmpsMap.put(dep, emps);
				}
				// 将员工添加到部门对应的员工列表中
				emps.add(emp);
			}
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.00");
		// 将结果写到Excel文件
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("部门名称");
			row.createCell(1).setCellValue("员工姓名");
			row.createCell(2).setCellValue("入职日期");
			row.createCell(3).setCellValue("工资");
			int rowNum = 1;// 行号
			for (Department dep : depList) {// 为了保证部门名称升序，遍历的是depList而不是depEmpsMap
				int empIndex = 0;// 员工的序号
				List<Employee> emps = depEmpsMap.get(dep);
				// 有无员工的部门，所以要做判断，否则出现空指针错误
				if (emps != null) {
					int personCount = 0;
					double salarySum = 0;
					for (Employee emp : emps) {
						cal.setTime(emp.getHireDate());
						if (cal.get(Calendar.YEAR) == 2008) {
							personCount++;
							salarySum += emp.getSalary();
							row = sheet.createRow(rowNum);
							// 如果员工序号为0，则输出部门名称
							if (empIndex == 0)
								row.createCell(0).setCellValue(dep.getDepartmentName());
							row.createCell(1).setCellValue(emp.getName());
							row.createCell(2).setCellValue(sdf.format(emp.getHireDate()));
							row.createCell(3).setCellValue(df.format(emp.getSalary()));
							empIndex++;
							rowNum++;
						}
					}
					if (personCount > 0) {
						row = sheet.createRow(rowNum);
						row.createCell(0).setCellValue("人员总数：");
						row.createCell(1).setCellValue(personCount);
						row.createCell(2).setCellValue("工资总额：");
						row.createCell(3).setCellValue(df.format(salarySum));
						rowNum++;
					}
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH+"/"+Exercise09.class.getSimpleName()+".xls"));
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
