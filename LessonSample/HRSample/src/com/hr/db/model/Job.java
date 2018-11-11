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

public class Job {
	private String jobId;
	private String jobTitle;
	private double minSalary;
	private double maxSalary;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
	public double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary="
				+ maxSalary + "]";
	}
	public static List<Job> readFromUTF8TextFile(String fileName) {
		return readFromUTF8TextFile(fileName,null);
	}
	public static List<Job> readFromUTF8TextFile(String fileName,List<CellDataError> errList) {
		return readFromTextFile(fileName, "UTF8",errList);
	}
	//
	public static List<Job> readFromTextFile(String fileName, String charset,List<CellDataError> errList){
		try {
			return readFromTextFile(new InputStreamReader(new FileInputStream(fileName), charset),errList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Job> readFromTextFile(Reader reader, List<CellDataError> errList) {
		List<Job> jobList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (i++ == 0 || line.isEmpty()) {
					continue;
				}

				String[] fields = line.split(",");
				if (fields.length < 4) {
					if (errList != null) {
						CellDataError cde = new CellDataError(i, "列数不足，至少的4列,当前是" + fields.length + "列");
						errList.add(cde);
					}
					continue;
				}
				boolean isValid = true;
				Job job= new Job();
				String jobId = fields[0].trim();
				if (!jobId.isEmpty())
					job.setJobId(jobId);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(i, 1, "jobId为空");
						errList.add(cde);
					}
				}

				String jobTitle = fields[1].trim();
				if (!jobTitle.isEmpty())
					job.setJobTitle(jobTitle);
				else {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(i, 2, "jobTitle为空");
						errList.add(cde);
					}
				}

				String minSalaryStr = fields[2].trim();
				try {
					double minSalary = Double.parseDouble(minSalaryStr);
					job.setMinSalary(minSalary);
				} catch (Exception e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(i, 3, "minSalary解析错误");
						errList.add(cde);
					}
				}

				String maxSalaryStr = fields[3].trim();
				try {
					double maxSalary = Double.parseDouble(maxSalaryStr);
					job.setMaxSalary(maxSalary);
				} catch (Exception e) {
					isValid = false;
					if (errList != null) {
						CellDataError cde = new CellDataError(i, 4, "maxSalary解析错误");
						errList.add(cde);
					}
				}

				if (isValid)
					jobList.add(job);
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
		return jobList;
	}	
	public static List<Job> readFromExcelFile(String fullFileName) {
		return readFromExcelFile(fullFileName, null);
	}

	public static List<Job> readFromExcelFile(String fullFileName, List<CellDataError> cdeList) {
		return readFromTextFile(new StringReader(StringUtils.excelToString(fullFileName,"job",',')), cdeList);
	}
	public static void main(String[] args) {
		List<CellDataError> cdeList = new ArrayList<CellDataError>();
		List<Job> jobList = readFromExcelFile(Contants.DATAPATH+"/hr-exam.xls",cdeList);
		for (Job job : jobList)
			System.out.println(job);
		for (CellDataError cde : cdeList)
			System.out.println(cde);
	}

}
