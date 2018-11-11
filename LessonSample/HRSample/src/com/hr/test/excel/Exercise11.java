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

public class Exercise11 {
	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH+"/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Job> jobList = Job.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();
		Map<String, Job> mapJob = new HashMap<>();
		for (Job job : jobList)
			mapJob.put(job.getJobId(), job);

		List<Department> depList = Department.readFromExcelFile(xlsFileName, cdeList);
		Collections.sort(depList, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		for (Department dep : depList)
			System.out.println(dep);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName, cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		cdeList.clear();

		Map<Integer, List<Employee>> depEmpsMap = new HashMap<Integer, List<Employee>>();
		Map<Integer, Set<String>> depJobsMap = new HashMap<>();
		for (Employee emp : empList) {
			if (emp.getDepartmentId() != null) {
				List<Employee> emps = depEmpsMap.get(emp.getDepartmentId());
				Set<String> jobs = depJobsMap.get(emp.getDepartmentId());
				if (emps == null) {
					emps = new ArrayList<Employee>();
					depEmpsMap.put(emp.getDepartmentId(), emps);
				}
				emps.add(emp);
				if (jobs == null) {
					jobs = new HashSet<String>();
					depJobsMap.put(emp.getDepartmentId(), jobs);
				}
				jobs.add(emp.getJobId());
			}
		}
		System.out.println(depJobsMap);
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("单位名称");
			row.createCell(1).setCellValue("工作名称");
			row.createCell(2).setCellValue("雇员人数");
			int totalPersons = 0;
			int rowNum = 1;
			for (Department dep : depList) {
				if (depJobsMap.keySet().contains(dep.getDepartmentId())) {
					Set<String> jobIds = depJobsMap.get(dep.getDepartmentId());
					List<Job> jobs = new ArrayList<>();
					for (String jobId : jobIds) {
						jobs.add(mapJob.get(jobId));
					}
					Collections.sort(jobs,new Comparator<Job>() {
						@Override
						public int compare(Job o1, Job o2) {
							return o1.getJobTitle().compareTo(o2.getJobTitle());
						}
					});
					for (int i = 0; i < jobs.size(); i++) {
						Job job = jobs.get(i);
						row = sheet.createRow(rowNum);
						if (i == 0)
							row.createCell(0).setCellValue(dep.getDepartmentName());
						row.createCell(1).setCellValue(job.getJobTitle());
						int personCount = 0;
						for(Employee emp : depEmpsMap.get(dep.getDepartmentId())){
							if(emp.getJobId().equals(job.getJobId())) {
								personCount ++;
							}
						}
						totalPersons += personCount;
						row.createCell(2).setCellValue(personCount);
						rowNum++;
					}
				}
			}
			row = sheet.createRow(rowNum);
			row.createCell(1).setCellValue("总人数");
			row.createCell(2).setCellValue(totalPersons);
			wb.write(new FileOutputStream(Contants.DATAPATH+"/"+Exercise11.class.getSimpleName()+".xls"));
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
