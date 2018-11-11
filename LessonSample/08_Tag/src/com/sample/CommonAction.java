package com.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.struts2.ServletActionContext;

import com.hr.db.model.Department;
import com.hr.db.model.Employee;
import com.hr.db.model.Job;
import com.hr.db.model.SalGrade;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {
	private String hrExcelFile = ServletActionContext.getServletContext().getRealPath("/WEB-INF/hrdata/hr-exam.xls");
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		addActionError("第一条错误消息！");
		addActionError("第二条错误消息！");
		addActionMessage("第一条普通消息！");
		addActionMessage("第二条普通消息！");
		return SUCCESS;
	}

	public List<Employee> getEmployees() {
		List<Employee> emps = Employee.readFromExcelFile(hrExcelFile);
		Collections.sort(emps, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return emps;
	}

	public List<Employee> getEmployees(Integer departmentId) {
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : getEmployees()) {
			if (departmentId.equals(emp.getDepartmentId()))
				emps.add(emp);
		}
		return emps;
	}

	public List<Department> getDepartments() {
		List<Department> deps = Department.readFromExcelFile(hrExcelFile);
		Collections.sort(deps, new Comparator<Department>() {
			@Override
			public int compare(Department o1, Department o2) {
				return o1.getDepartmentName().compareTo(o2.getDepartmentName());
			}
		});
		return deps;
	}

	public List<Job> getJobs() {
		List<Job> jobs = Job.readFromExcelFile(hrExcelFile);
		Collections.sort(jobs, new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.getJobTitle().compareTo(o2.getJobTitle());
			}
		});
		return jobs;
	}

	public List<SalGrade> getSalGrades() {
		List<SalGrade> sgList = SalGrade.readFromExcelFile(hrExcelFile);
		Collections.sort(sgList, new Comparator<SalGrade>() {
			@Override
			public int compare(SalGrade o1, SalGrade o2) {
				return o1.getGradeLevel().compareTo(o2.getGradeLevel());
			}
		});
		return sgList;
	}
	private String city="Washington";

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		System.out.println("setCity(\""+city+"\")");
		this.city = city;
	}
			
}
