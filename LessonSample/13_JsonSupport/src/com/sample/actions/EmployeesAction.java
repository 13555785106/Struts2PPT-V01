package com.sample.actions;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import com.hr.db.model.Employee;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeesAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public List<Employee> getEmployees(){
		return Employee.readFromExcelFile(ServletActionContext.getServletContext().getRealPath("/WEB-INF/hrdata/hr-exam.xls"));
	}


}
