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

import com.hr.test.Contants;
import com.hr.util.StringUtils;

public class SalGrade {
	String gradeLevel;
	Double lowestSalary;
	Double highestSalary;

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public Double getLowestSalary() {
		return lowestSalary;
	}

	public void setLowestSalary(Double lowestSalary) {
		this.lowestSalary = lowestSalary;
	}

	public Double getHighestSalary() {
		return highestSalary;
	}

	public void setHighestSalary(Double highestSalary) {
		this.highestSalary = highestSalary;
	}

	@Override
	public String toString() {
		return "SalGrade [gradeLevel=" + gradeLevel + ", lowestSalary=" + lowestSalary + ", highestSalary="
				+ highestSalary + "]";
	}
	public static List<SalGrade> readFromUTF8TextFile(String fileName) {
		return readFromUTF8TextFile(fileName, null);
	}
	public static List<SalGrade> readFromUTF8TextFile(String fileName, List<CellDataError> errList) {
		return readFromTextFile(fileName, "UTF8", errList);
	}
	public static List<SalGrade> readFromTextFile(String fileName,String charset,List<CellDataError> errList){
		try {
			return readFromTextFile(new InputStreamReader(new FileInputStream(fileName), charset),errList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<SalGrade> readFromTextFile(Reader reader, List<CellDataError> errList) {
		List<SalGrade> salGradeList = new ArrayList<>();
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
				SalGrade salGrade = new SalGrade();

				String grageLevel = fields[0].trim();
				if (!grageLevel.isEmpty())
					salGrade.setGradeLevel(grageLevel);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(rowNum, 1, "grageLevel为空");
						errList.add(cde);
					}
				}

				String lowestSalaryStr = fields[1].trim();
				if (!lowestSalaryStr.isEmpty()) {
					try {
						double lowestSalary = (int)Double.parseDouble(lowestSalaryStr);
						salGrade.setLowestSalary(lowestSalary);
					} catch (NumberFormatException nfe) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 2, "lowestSalary解析错误");
							errList.add(cde);
						}
					}
				}

				String highestSalaryStr = fields[2].trim();
				if (!highestSalaryStr.isEmpty()) {
					try {
						double highestSalary = (int)Double.parseDouble(highestSalaryStr);
						salGrade.setHighestSalary(highestSalary);
					} catch (NumberFormatException nfe) {
						isValid = false;
						if (errList != null) {
							CellDataError cde = new CellDataError(rowNum, 2, "highestSalary解析错误");
							errList.add(cde);
						}
					}
				}

				if (isValid)
					salGradeList.add(salGrade);
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
		return salGradeList;
	}
	public static List<SalGrade> readFromExcelFile(String fullFileName) {
		return readFromExcelFile(fullFileName, null);
	}

	public static List<SalGrade> readFromExcelFile(String fullFileName, List<CellDataError> cdeList) {
		return readFromTextFile(new StringReader(StringUtils.excelToString(fullFileName,"salgrade",',')), cdeList);
	}
	public static void main(String[] args) {

		System.out.println(StringUtils.excelToString(Contants.DATAPATH+"/hr-exam.xls","salgrade",','));
		List<CellDataError> cdeList = new ArrayList<>();
		List<SalGrade> salGradeList = readFromExcelFile(Contants.DATAPATH+"/hr-exam.xls",cdeList);
		System.out.println(salGradeList.size());
		for (SalGrade salGrade : salGradeList)
			System.out.println(salGrade);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
	}

}
