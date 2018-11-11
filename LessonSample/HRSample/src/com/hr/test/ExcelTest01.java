package com.hr.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelTest01 {

	public static void main(String[] args) {
		//readExcel();
		writeExcel();
	}

	public static void readExcel() {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook(new FileInputStream("D:\\sample.xls"));
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				System.out.println(row.getLastCellNum());
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if (cell != null)
						System.out.print(cell.toString() + " ");
					else
						System.out.print("空 ");
				}
				System.out.println();
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
	}
	public static void writeExcel() {
		Workbook wb = null;
		try {
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();
			for (int i = 0; i < 10; i++) {
				if(i== 3) continue;
				Row row = sheet.createRow(i);
				Cell c1 = row.createCell(0);
				c1.setCellValue("A" + i);
				if (i % 2 == 0) {
					Cell c2 = row.createCell(1);
					c2.setCellValue("B" + i);
				}
				if (i % 2 == 1) {
					Cell c3 = row.createCell(2);
					c3.setCellValue("C" + i);
				}
			}
			wb.write(new FileOutputStream("D:\\z.xls"));
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
