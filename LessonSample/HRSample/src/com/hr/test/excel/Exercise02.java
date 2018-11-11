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
import com.hr.db.model.Job;
import com.hr.test.Contants;

public class Exercise02 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH+"/hr-exam.xls";
		List<CellDataError> cdeList = new ArrayList<>();
		List<Employee> employeeList = Employee.readFromExcelFile(xlsFileName, cdeList);
		cdeList.clear();
		List<Job> jobList = Job.readFromExcelFile(xlsFileName, cdeList);
		Collections.sort(jobList, new Comparator<Job>() {

			@Override
			public int compare(Job job1, Job job2) {
				return job1.getJobTitle().compareTo(job2.getJobTitle());
			}

		});
		Map<String, Double> jobMaxSalaryMap = new HashMap<>();
		Map<String, Double> jobMinSalaryMap = new HashMap<>();
		for (Employee employee : employeeList) {
			String jobId = employee.getJobId();
			Double curMaxSalary = jobMaxSalaryMap.get(jobId);
			if (curMaxSalary == null)
				curMaxSalary = Double.MIN_VALUE;
			Double curMinSalary = jobMinSalaryMap.get(jobId);
			if (curMinSalary == null)
				curMinSalary = Double.MAX_VALUE;

			if (employee.getSalary() > curMaxSalary)
				jobMaxSalaryMap.put(jobId, employee.getSalary());
			if (employee.getSalary() < curMinSalary)
				jobMinSalaryMap.put(jobId, employee.getSalary());

		}
		System.out.println(jobMinSalaryMap);
		System.out.println(jobMaxSalaryMap);
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("工作名称");
			row.createCell(1).setCellValue("最低工资");
			row.createCell(2).setCellValue("最高工资");
			int i = 1;
			for (Job job : jobList) {
				row = sheet.createRow(i);
				row.createCell(0).setCellValue(job.getJobTitle());
				Double minSalary = jobMinSalaryMap.get(job.getJobId());
				Double maxSalary = jobMaxSalaryMap.get(job.getJobId());
				if (minSalary != null)
					row.createCell(1).setCellValue(minSalary);
				if (maxSalary != null)
					row.createCell(2).setCellValue(maxSalary);
				i++;
			}
			wb.write(new FileOutputStream(Contants.DATAPATH+"/"+Exercise02.class.getSimpleName()+".xls"));
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
