package com.hr.test.text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.db.model.Job;
import com.hr.test.Contants;

public class TextExam04 {

	public static void main(String[] args) {
		List<Employee> empList = Employee.readFromUTF8TextFile(Contants.DATAPATH+"/employees.txt");
		List<Department> depList = Department.readFromUTF8TextFile(Contants.DATAPATH+"/departments.txt");
		List<Job> jobList = Job.readFromUTF8TextFile(Contants.DATAPATH+"/jobs.txt");
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
		StringBuilder sb = new StringBuilder();
		for (Department dep : depList) {
			if (depIdJobsEmpsmap.containsKey(dep.getDepartmentId())) {
				// 部门人数
				int depEmpCount = 0;
				StringBuilder sbJobsEmps = new StringBuilder();
				Map<String, List<Employee>> jobEmpsMap = depIdJobsEmpsmap.get(dep.getDepartmentId());
				for (Job job : jobList) {
					if (jobEmpsMap.containsKey(job.getJobId())) {
						List<Employee> emps = jobEmpsMap.get(job.getJobId());
						sbJobsEmps.append("    " + job.getJobTitle() + " " + emps.size() + "(人)\r\n");
						depEmpCount += emps.size();
						for (Employee emp : emps) {
							sbJobsEmps.append("        " + emp.getName() + "\r\n");
						}
					}
				}
				sb.append(dep.getDepartmentName() + " " + depEmpCount + "(人)\r\n");
				sb.append(sbJobsEmps);
			}
		}
		System.out.println(sb);
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH+"/"+TextExam04.class.getSimpleName()+".txt"), "utf8"));
			br.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
