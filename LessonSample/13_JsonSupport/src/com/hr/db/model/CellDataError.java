package com.hr.db.model;

public class CellDataError {
	private int rowNum = -1;
	private int colNum = -1;
	private String message;
	public CellDataError(int rowNum,String message) {
		this.rowNum = rowNum;
		this.message = message;
	}
	public CellDataError(int rowNum,int colNum,String message) {
		this(rowNum,message);
		this.colNum = colNum;
	}	
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CellDataError [rowNum=" + rowNum + ", colNum=" + colNum + ", message=" + message + "]";
	}
}
