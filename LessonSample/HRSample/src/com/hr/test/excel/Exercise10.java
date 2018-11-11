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
import com.hr.db.model.Job;
import com.hr.test.Contants;

public class Exercise10 {

	public static void main(String[] args) {
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH + "/employees.txt");
		List<Department> depList = Department.readFromUTF8TextFile(Contants.DATAPATH + "/departments.txt");
		List<Job> jobList = Job.readFromUTF8TextFile(Contants.DATAPATH + "/jobs.txt");
		// 排序
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		Collections.sort(jobList, new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.getJobTitle().compareTo(o2.getJobTitle());
			}
		});
		// Map<部门id, 部门对象> 方便根据id获取对象
		Map<Integer, Department> depMap = new HashMap<Integer, Department>();
		for (Department dep : depList)
			depMap.put(dep.getDepartmentId(), dep);
		// Map<工作id, 工作对象> 方便根据id获取对象
		Map<String, Job> jobMap = new HashMap<String, Job>();
		for (Job job : jobList)
			jobMap.put(job.getJobId(), job);
		// Map<部门id, Map<工作id, 雇员列表>>
		Map<Integer, Map<String, List<Employee>>> depIdJobsEmpsmap = new HashMap<Integer, Map<String, List<Employee>>>();
		// 遍历雇员列表，构建上面的Map
		for (Employee emp : empList) {
			if (emp.getDepartmentId() != null) {// 如果雇员的部门id不为空，说明有部门
				// 获取部门id对应的 工作id雇员列表 Map
				Map<String, List<Employee>> jobEmpsMap = depIdJobsEmpsmap.get(emp.getDepartmentId());
				if (jobEmpsMap == null) {
					jobEmpsMap = new HashMap<String, List<Employee>>();
				}
				depIdJobsEmpsmap.put(emp.getDepartmentId(), jobEmpsMap);
				// 根据工作id获取对应的雇员列表
				List<Employee> emps = jobEmpsMap.get(emp.getJobId());
				if (emps == null) {
					emps = new ArrayList<Employee>();
					jobEmpsMap.put(emp.getJobId(), emps);
				}
				emps.add(emp);
			}
		}

		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("部门名称");
			row.createCell(1).setCellValue("人数");
			row.createCell(2).setCellValue("工作名称");
			row.createCell(3).setCellValue("人数");
			row.createCell(4).setCellValue("雇员姓名");
			int rowNum = 0;// 行号
			for (Department dep : depList) {
				if (depIdJobsEmpsmap.containsKey(dep.getDepartmentId())) {
					rowNum++;
					int depRowNum = rowNum;
					sheet.createRow(rowNum).createCell(0).setCellValue("--");
					int depEmpCount = 0;
					Map<String, List<Employee>> jobEmpsMap = depIdJobsEmpsmap.get(dep.getDepartmentId());
					boolean firstJob = true;
					for (Job job : jobList) {
						if (jobEmpsMap.containsKey(job.getJobId())) {
							if (firstJob) {
								firstJob = false;
								row = sheet.getRow(rowNum);
							} else {
								rowNum++;
								row = sheet.createRow(rowNum);
							}
							List<Employee> emps = jobEmpsMap.get(job.getJobId());
							row.createCell(2).setCellValue(job.getJobTitle());
							row.createCell(3).setCellValue(emps.size());
							depEmpCount += emps.size();
							boolean firstEmp = true;
							for (Employee emp : emps) {
								if (firstEmp) {
									firstEmp = false;
									row = sheet.getRow(rowNum);
								} else {
									rowNum++;
									row = sheet.createRow(rowNum);
								}
								row.createCell(4).setCellValue(emp.getName());
							}
						}
					}
					sheet.getRow(depRowNum).createCell(0).setCellValue(dep.getDepartmentName());
					sheet.getRow(depRowNum).createCell(1).setCellValue(depEmpCount);
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise10.class.getSimpleName() + ".xls"));
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
