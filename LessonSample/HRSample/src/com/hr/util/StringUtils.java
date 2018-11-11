package com.hr.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public final class StringUtils {
	private StringUtils() {
	}

	/*
	 * str 被填充的字符串 c 填充字符 width 为填充后的宽度 右侧填充字符，如果width小于字符串长度，右侧的多余字符被截断。
	 */
	public static String rpad(String str, char c, int width) {
		StringBuilder sb = new StringBuilder(str);
		if (width <= str.length())
			sb.delete(width, str.length());
		else {
			for (int i = 0; i < width - str.length(); i++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/*
	 * str 被填充的字符串 c 填充字符 width 为填充后的宽度 左侧填充字符，如果width小于字符串长度，左侧的多余字符被截断。
	 */
	public static String lpad(String str, char c, int width) {
		StringBuilder sb = new StringBuilder(str);
		if (width <= str.length())
			sb.delete(0, str.length() - width);
		else {
			for (int i = 0; i < width - str.length(); i++) {
				sb.insert(0, c);
			}
		}
		return sb.toString();
	}

	public static int calCharCount(String str, char c) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c)
				count++;
		}
		return count;
	}
	public static String[] splitWithWhiteSpace(String str) {
		List<String> strList = new ArrayList<String>();
		for(String s : str.split(" "))
			strList.add(s);
		for(int i=0;i<strList.size();i++) {
			if(strList.get(i).isEmpty())
				strList.remove(i--);
		}
		return strList.toArray(new String[] {});
	}
	
	public static boolean containContinuousSameChar(String str,int count) {
		for(int i=0;i<str.length()-1;i++) {
			int amount = 0;
			for(int j=i+1;j<str.length();j++) {
				if(str.charAt(i)==str.charAt(j))
					amount++;
				if(amount+1>=count)
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String str = "1358884567";
		System.out.println(containContinuousSameChar(str,4));
		/*String str = "apple   bee    zero";
		for(String s :str.split(" "))
			System.out.print("["+s+"]");
		System.out.println();
		for(String s :splitWithWhiteSpace(str))
			System.out.print("["+s+"]");
		System.out.println();*/
	
		
		/*for(int i=0;i<15;i++)
			System.out.print("-");
		System.out.println();
		System.out.println(lpad("0123456789", '*', 15));
		System.out.println(lpad("0123456789", '*', 5));
		System.out.println(rpad("0123456789", '*', 15));
		System.out.println(rpad("0123456789", '*', 5));
		for(int i=0;i<15;i++)
			System.out.print("-");
		System.out.println();*/
	}
	//将一个Excel文件中的Sheet中的数据转换为字符串
	//每一列之间使用指定的分隔符分割
	public static String excelToString(String fullFileName,String sheetName,char separator) {
		StringBuilder sb = new StringBuilder();
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(new FileInputStream(fullFileName));
			Sheet sheet = wb.getSheet(sheetName);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j);
						if (cell != null)
							sb.append(cell.toString());
						if (j != row.getLastCellNum() - 1)
							sb.append(separator);
					}
				}
				sb.append("\r\n");
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
		return sb.toString();
	}
}
