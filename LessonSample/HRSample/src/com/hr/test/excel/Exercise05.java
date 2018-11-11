package com.hr.test.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import com.hr.db.model.Employee;
import com.hr.db.model.Job;
import com.hr.test.Contants;

public class Exercise05 {

	public static void main(String[] args) {
		String xlsFileName = Contants.DATAPATH + "/hr-exam.xls";
		List<Employee> empList = Employee.readFromExcelFile(xlsFileName);
		Collections.sort(empList, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		List<Job> jobList = Job.readFromExcelFile(xlsFileName);
		Collections.sort(jobList, new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.getJobTitle().compareTo(o2.getJobTitle());
			}
		});
		Map<String, Job> jobMap = new HashMap<String, Job>();
		for (Job job : jobList)
			jobMap.put(job.getJobId(), job);

		Map<String, List<Employee>> jobIdEmpsMap = new HashMap<String, List<Employee>>();
		for (Employee emp : empList) {
			List<Employee> emps = jobIdEmpsMap.get(emp.getJobId());
			if (emps == null) {
				emps = new ArrayList<Employee>();
				jobIdEmpsMap.put(emp.getJobId(), emps);
			}
			double actualSalary = emp.getSalary()*(1 + (emp.getCommissionPct()!=null?emp.getCommissionPct():0));
			if(actualSalary >= jobMap.get(emp.getJobId()).getMaxSalary())
				emps.add(emp);
		}
		DecimalFormat df = new DecimalFormat("#.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("工作名称");
			row.createCell(1).setCellValue("薪水上限");
			row.createCell(2).setCellValue("雇员姓名");
			row.createCell(3).setCellValue("实发薪水");
			row.createCell(4).setCellValue("入职日期");
			int rowNum = 1;// 行号
			for(Job job : jobList) {
				if(jobIdEmpsMap.containsKey(job.getJobId())) {
					List<Employee> emps = jobIdEmpsMap.get(job.getJobId());
					for(int i=0;i<emps.size();i++) {
						Employee emp = emps.get(i);
						double actualSalary = emp.getSalary()*(1 + (emp.getCommissionPct()!=null?emp.getCommissionPct():0));
						row = sheet.createRow(rowNum);
						if(i==0) {
							row.createCell(0).setCellValue(job.getJobTitle());
							row.createCell(1).setCellValue(df.format(job.getMaxSalary()));
						}
						row.createCell(2).setCellValue(emp.getName());
						row.createCell(3).setCellValue(df.format(actualSalary));
						row.createCell(4).setCellValue(sdf.format(emp.getHireDate()));
						rowNum++;
					}
					
				}
			}
			wb.write(new FileOutputStream(Contants.DATAPATH + "/" + Exercise05.class.getSimpleName() + ".xls"));
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
