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

import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class Exercise03 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH+"/hr-exam.xls";
		List<Department> depList = Department.readFromExcelFile(xlsFileName);
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		//Map<雇员id, 雇员对象> 方便根据id获取对象
		Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();
		for (Employee emp : empList)
			empMap.put(emp.getEmployeeId(), emp);

		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee e1, Employee e2) {
				return e1.getName().compareTo(e2.getName());
			}
		});
		//Map<部门id, 雇员列表> 方便根据部门id获取该部门的雇员列表
		Map<Integer, List<Employee>> depEmpsMap = new HashMap<Integer, List<Employee>>();
		for (Employee emp : empList) {
			Integer departmentId = emp.getDepartmentId();
			//获取部门id对应的雇员列表
			List<Employee> list = depEmpsMap.get(departmentId);
			if (list == null) {
				//如果为空，则创建一个空的雇员列表压进去
				list = new ArrayList<Employee>();
				depEmpsMap.put(departmentId, list);
			}
			list.add(emp);//将相应的雇员放到所在部门的列表中。
		}
		

		/*for (Department dep : depList) {
			List<Employee> emps = depEmpsMap.get(dep.getDepartmentId());
			if (emps == null) {
				sb.append(dep.getDepartmentName()).append(" 无人\r\n");

			}
		}*/
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("部门名称");
			row.createCell(1).setCellValue("部门主管");
			row.createCell(2).setCellValue("雇员姓名");
			row.createCell(3).setCellValue("部门人数");
			int rowNum = 1;// 行号
			for (Department dep : depList) {
				List<Employee> emps = depEmpsMap.get(dep.getDepartmentId());
				if (emps != null) {
					boolean first = true;
					for (Employee emp : emps) {
						row = sheet.createRow(rowNum);
						if(first) {
							first = false;
							row.createCell(0).setCellValue(dep.getDepartmentName());
							row.createCell(1).setCellValue(empMap.get(dep.getManagerId()).getName());
							row.createCell(3).setCellValue(emps.size());
						}
						row.createCell(2).setCellValue(emp.getName());
						rowNum++;
					}
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH+"/"+Exercise03.class.getSimpleName()+".xls"));
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
