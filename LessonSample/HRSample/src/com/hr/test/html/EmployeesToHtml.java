package com.hr.test.html;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.hr.db.model.CellDataError;
import com.hr.db.model.Employee;
import com.hr.test.Contants;

public class EmployeesToHtml {

	public static void main(String[] args) {
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Employee> empList = Employee.readFromExcelFile(Contants.DATAPATH+"/hr-exam.xls", cdeList);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
		Collections.sort(empList, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
		String[] titles = { "EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PHONE_NUMBER", "HIRE_DATE", "JOB_ID",
				"SALARY", "COMMISSION_PCT", "MANAGER_ID", "DEPARTMENT_ID" };
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\r\n" + "<head>\r\n" + "<link rel=\"stylesheet\" type=\"text/css\" href=\"employees.css\">\r\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
				+ "<title>employees</title>\r\n" + "</head>\r\n" + "<body style=\"font-size:32px\">\r\n"
				+ "<table align=\"center\" >\r\n" + "<caption>Employees</caption>");
		sb.append("<tr>\r\n");
		for (String title : titles)
			sb.append("<th>").append(title).append("</th>\r\n");
		sb.append("</tr>\r\n");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < empList.size(); i++) {
			Employee emp = empList.get(i);
			if (i % 2 == 1)
				sb.append("<tr class=\"oddrow\">\r\n");
			else
				sb.append("<tr class=\"evenrow\">\r\n");
			sb.append("<td>").append(emp.getEmployeeId()).append("</td>\r\n");
			sb.append("<td>").append(emp.getFirstName()).append("</td>\r\n");
			sb.append("<td>").append(emp.getLastName()).append("</td>\r\n");
			sb.append("<td>").append(emp.getEmail()).append("</td>\r\n");
			sb.append("<td>").append(emp.getPhoneNumber()).append("</td>\r\n");
			sb.append("<td>").append(sdf.format(emp.getHireDate())).append("</td>\r\n");
			sb.append("<td>").append(emp.getJobId()).append("</td>\r\n");
			sb.append("<td>").append(df.format(emp.getSalary())).append("</td>\r\n");
			sb.append("<td>");
			if (emp.getCommissionPct() == null)
				sb.append("&nbsp;");
			else
				sb.append(emp.getCommissionPct());
			sb.append("</td>\r\n");
			sb.append("<td>").append(emp.getManagerId()).append("</td>\r\n");
			sb.append("<td>");
			if (emp.getDepartmentId() == null)
				sb.append("&nbsp;");
			else
				sb.append(emp.getDepartmentId());
			sb.append("</td>\r\n");
			sb.append("</tr>\r\n");
		}
		sb.append("</table>\r\n" + "</body>\r\n" + "</html>");
		System.out.println(sb);
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(Contants.DATAPATH + "/" + "employees.html"), "utf8"));
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
