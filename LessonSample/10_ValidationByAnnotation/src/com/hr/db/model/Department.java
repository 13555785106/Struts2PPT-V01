package com.hr.db.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class Department {
	private Integer departmentId;
	private String departmentName;
	private Integer managerId;
	private Integer locationId;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId="
				+ (managerId != null ? managerId : "null") + ", locationId="
				+ (locationId != null ? locationId : "null") + "]";
	}
	public static List<Department> readFromUTF8TextFile(String fileName) {
		return readFromUTF8TextFile(fileName, null);
	}
	public static List<Department> readFromUTF8TextFile(String fileName, List<CellDataError> errList) {
		return readFromTextFile(fileName, "UTF8", errList);
	}
	public static List<Department> readFromTextFile(String fileName,String charset,List<CellDataError> errList){
		try {
			return readFromTextFile(new InputStreamReader(new FileInputStream(fileName), charset),errList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Department> readFromTextFile(Reader reader, List<CellDataError> errList) {
		List<Department> departmentList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				int rowNum = i+1;
				line = line.trim();
				if (i++ == 0 || line.isEmpty()) {
					continue;
				}

				String[] fields = line.split(",");
				if (fields.length < 3) {
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, "列数不足，至少的3列,当前是" + fields.length + "列");
						errList.add(cde);
					}
					continue;
				}
				boolean isValid = true;
				Department department = new Department();
				try {
					int departmentId = (int)Double.parseDouble(fields[0].trim());
					department.setDepartmentId(departmentId);
				} catch (Exception e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 1, "id解析错误");
						errList.add(cde);
					}
				}

				String departmentName = fields[1].trim();
				if (!departmentName.isEmpty())
					department.setDepartmentName(departmentName);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 2, "departmentName为空");
						errList.add(cde);
					}
				}

				String managerIdStr = fields[2].trim();
				if (!managerIdStr.isEmpty()) {
					try {
						int managerId = (int)Double.parseDouble(managerIdStr);
						department.setManagerId(managerId);
					} catch (NumberFormatException nfe) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 3, "managerId解析错误");
							errList.add(cde);
						}
					}
				}

				if (fields.length > 3) {
					try {
						String locationIdStr = fields[3].trim();
						int locationId = (int)Double.parseDouble(locationIdStr);
						department.setLocationId(locationId);
					} catch (Exception e) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 4, "locationId解析错误");
							errList.add(cde);
						}
					}
				}

				if (isValid)
					departmentList.add(department);
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
		return departmentList;
	}

	@Override
	public int hashCode() {
		return departmentId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Department) {
			Department other = (Department) obj;
			return this.departmentId == other.getDepartmentId();
		}
		return false;
	}

	public static List<Department> readFromExcelFile(String fullFileName) {
		return readFromExcelFile(fullFileName, null);
	}

	public static List<Department> readFromExcelFile(String fullFileName, List<CellDataError> cdeList) {
		return readFromTextFile(new StringReader(StringUtils.excelToString(fullFileName,"dep",',')), cdeList);
	}
	public static void main(String[] args) {

	}
}
