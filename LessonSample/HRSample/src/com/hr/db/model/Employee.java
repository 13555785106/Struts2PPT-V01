package com.hr.db.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hr.test.Contants;
import com.hr.util.StringUtils;

public class Employee {
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double salary;
	private Float commissionPct;
	private Integer managerId;
	private Integer departmentId;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static DecimalFormat df = new DecimalFormat("0.00");

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Float getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(Float commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + sdf.format(hireDate) + ", jobId=" + jobId
				+ ", salary=" + df.format(salary) + ", commissionPct="
				+ (commissionPct != null ? df.format(commissionPct) : "null") + ", managerId="
				+ (managerId != null ? managerId : "null") + ", departmentId="
				+ (departmentId != null ? departmentId : "null") + "]";
	}

	public static List<Employee> readFromUTF8TextFile(String fileName) {
		return readFromUTF8TextFile(fileName, null);
	}

	public static List<Employee> readFromUTF8TextFile(String fileName, List<CellDataError> errList) {
		return readFromTextFile(fileName, "UTF8", errList);
	}

	public static List<Employee> readFromTextFile(String fileName, String charset, List<CellDataError> errList) {
		try {
			return readFromTextFile(new InputStreamReader(new FileInputStream(fileName), charset), errList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Employee> readFromTextFile(Reader reader, List<CellDataError> errList) {
		List<Employee> empList = new ArrayList<Employee>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				int rowNum = i + 1;
				line = line.trim();
				if (i++ == 0 || line.isEmpty())
					continue;
				String[] fields = line.split(",");
				if (fields.length < 10) {
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, "列数不足，至少需要10列,当前是" + fields.length + "列");
						errList.add(cde);
					}
					continue;
				}
				boolean isValid = true;
				Employee employee = new Employee();

				try {
					int employeeId = (int) Double.parseDouble(fields[0].trim());
					employee.setEmployeeId(employeeId);
				} catch (Exception e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 1, "id解析错误");
						errList.add(cde);
					}
				}

				String firstName = fields[1].trim();
				if (!firstName.isEmpty())
					employee.setFirstName(firstName);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 2, "firstName为空");
						errList.add(cde);
					}
				}

				String lastName = fields[2].trim();
				if (!lastName.isEmpty())
					employee.setLastName(lastName);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 3, "lastName为空");
						errList.add(cde);
					}
				}

				String email = fields[3].trim();
				if (!email.isEmpty())
					employee.setEmail(email);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 4, "email为空");
						errList.add(cde);
					}
				}

				String phoneNumber = fields[4].trim();
				if (!phoneNumber.isEmpty())
					employee.setPhoneNumber(phoneNumber);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 5, "phoneNumber为空");
						errList.add(cde);
					}
				}

				String hireDateStr = fields[5].trim();
				try {
					Date hireDate = sdf.parse(hireDateStr);
					employee.setHireDate(hireDate);
				} catch (ParseException e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 6, "hireDate解析错误");
						errList.add(cde);
					}
				}

				String jobId = fields[6].trim();
				if (!jobId.isEmpty())
					employee.setJobId(jobId);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 7, "jobId为空");
						errList.add(cde);
					}
				}

				String salaryStr = fields[7].trim();
				try {
					double salary = Double.parseDouble(salaryStr);
					employee.setSalary(salary);
				} catch (Exception e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 8, "salary解析错误");
						errList.add(cde);
					}
				}

				String commissionPctStr = fields[8].trim();
				if (!commissionPctStr.isEmpty()) {
					try {
						float commissionPct = Float.parseFloat(commissionPctStr);
						employee.setCommissionPct(commissionPct);
					} catch (NumberFormatException nfe) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 9, "commissionPct解析错误");
							errList.add(cde);
						}
					}
				}

				String managerIdStr = fields[9].trim();
				if (!managerIdStr.isEmpty()) {
					try {
						int managerId = (int) Double.parseDouble(managerIdStr);
						employee.setManagerId(managerId);
					} catch (NumberFormatException nfe) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 10, "managerId解析错误");
							errList.add(cde);
						}
					}
				}

				if (fields.length > 10) {
					try {
						String departmentIdStr = fields[10].trim();
						int departmentId = (int) Double.parseDouble(departmentIdStr);
						employee.setDepartmentId(departmentId);
					} catch (Exception e) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 11, "departmentId解析错误");
							errList.add(cde);
						}
					}
				}
				if (isValid)
					empList.add(employee);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return empList;
	}

	public static List<Employee> readFromExcelFile(String fullFileName) {
		return readFromExcelFile(fullFileName, null);
	}

	public static List<Employee> readFromExcelFile(String fullFileName, List<CellDataError> cdeList) {
		return readFromTextFile(new StringReader(StringUtils.excelToString(fullFileName,"emp",',')), cdeList);
	}
	/*	public static List<Employee> readFromExcelFile(String fullFileName, List<CellDataError> cdeList) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Employee> empList = new ArrayList<Employee>();
			Workbook wb = null;
			try {
				wb = new HSSFWorkbook(new FileInputStream(fullFileName));
				Sheet sheet = wb.getSheet("emp");
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					int rowNum = i + 1;
					Row row = sheet.getRow(i);
					if (row == null)
						continue;
					if (row.getLastCellNum() < 10) {
						if(cdeList !=null)
							cdeList.add(new CellDataError(rowNum, "列数为" + row.getLastCellNum() + ",至少为10"));
						continue;
					}
					boolean allIsValid = true;
					Employee emp = new Employee();
					Cell employeeIdCell = row.getCell(0);
					try {
						emp.setEmployeeId(new Double(employeeIdCell.toString()).intValue());
					} catch (Exception e) {
						e.printStackTrace();
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 1, "EMPLOYEE_ID为无效数字"));
						}
					}
					Cell firstNameCell = row.getCell(1);
					String tmp = null;
					if (firstNameCell != null && !(tmp = firstNameCell.toString().trim()).isEmpty())
						emp.setFirstName(tmp);
					else {
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 2, "FIRST_NAME为空"));
						}
					}
					Cell lastNameCell = row.getCell(2);
					if (lastNameCell != null && !(tmp = lastNameCell.toString().trim()).isEmpty())
						emp.setLastName(tmp);
					else {
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 3, "LAST_NAME为空"));
						}
					}
	
					Cell emailCell = row.getCell(3);
					if (emailCell != null && !(tmp = emailCell.toString().trim()).isEmpty())
						emp.setEmail(tmp);
					else {
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 4, "EMAIL为空"));
						}
					}
	
					Cell phoneNumberCell = row.getCell(4);
					if (phoneNumberCell != null && !(tmp = phoneNumberCell.toString().trim()).isEmpty())
						emp.setPhoneNumber(tmp);
					else {
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 5, "PHONE_NUMBER为空"));
						}
					}
	
					try {
						emp.setHireDate(sdf.parse(row.getCell(5).toString()));
					} catch (Exception e) {
						e.printStackTrace();
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 6, "HIRE_DATE日期格式错误"));
						}
					}
	
					Cell jobIdCell = row.getCell(6);
					if (jobIdCell != null && !(tmp = jobIdCell.toString().trim()).isEmpty())
						emp.setJobId(tmp);
					else {
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 7, "JOB_ID为空"));
						}
					}
					Cell salaryCell = row.getCell(7);
					try {
						emp.setSalary(Double.parseDouble(salaryCell.toString()));
					} catch (Exception e) {
						e.printStackTrace();
						allIsValid = false;
						if (cdeList != null) {
							cdeList.add(new CellDataError(rowNum, 8, "SALARY数字格式错误"));
						}
					}
					String commissionPctStr = "";
					Cell commissionPctCell = row.getCell(8);
					if (commissionPctCell != null)
						commissionPctStr = commissionPctCell.toString().trim();
					if (!commissionPctStr.isEmpty()) {
						try {
							emp.setCommissionPct(new Float(commissionPctStr));
						} catch (Exception e) {
							e.printStackTrace();
							allIsValid = false;
							if (cdeList != null) {
								cdeList.add(new CellDataError(rowNum, 9, "COMMISSION_PCT数字格式错误"));
							}
						}
					}
					Cell managerIdCell = row.getCell(9);
					String managerIdStr = "";
					if (managerIdCell != null)
						managerIdStr = managerIdCell.toString().trim();
					if (!managerIdStr.isEmpty()) {
						try {
							emp.setManagerId(new Double(managerIdStr).intValue());
						} catch (Exception e) {
							e.printStackTrace();
							allIsValid = false;
							if (cdeList != null) {
								cdeList.add(new CellDataError(rowNum, 10, "MANAGER_ID数字格式错误"));
							}
						}
					}
					String departmentIdStr = "";
					Cell departmentIdCell = row.getCell(10);
					if (departmentIdCell != null)
						departmentIdStr = departmentIdCell.toString().trim();
					if (!departmentIdStr.isEmpty()) {
						try {
							emp.setDepartmentId(new Double(departmentIdStr).intValue());
						} catch (Exception e) {
							e.printStackTrace();
							allIsValid = false;
							if (cdeList != null) {
								cdeList.add(new CellDataError(rowNum, 11, "DEPARTMENT_ID数字格式错误"));
							}
						}
					}
					if (allIsValid)
						empList.add(emp);
				}
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
			return empList;
		}*/

	public static void main(String[] args) {
		List<CellDataError> cdeList = new ArrayList<>();
		System.out.println(StringUtils.excelToString(Contants.DATAPATH+"/hr-exam.xls","emp",','));
		List<Employee> empList = readFromExcelFile(Contants.DATAPATH+"/hr-exam.xls", cdeList);
		for (Employee emp : empList)
			System.out.println(emp);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
	}
}
