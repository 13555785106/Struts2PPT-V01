package com.telecom.geometry;

public class Square extends Rectangle {
	public Square(double length) {
		setName("正方形");
		setLength(length);
	}
	@Override
	public void setWidth(double width) {
		throw new NotAllowedMethodException("此方法被禁用，请使用setLength函数");
	}
	@Override
	public double getWidth() {
		throw new NotAllowedMethodException("此方法被禁用，请使用getLength函数");
	}
	@Override
	public void setHeight(double height) {
		throw new NotAllowedMethodException("此方法被禁用，请使用setLength函数");
	}
	@Override
	public double getHeight() {
		throw new NotAllowedMethodException("此方法被禁用，请使用getLength函数");
	}	
	public void setLength(double length) {
		super.setWidth(length);
		super.setHeight(length);
	}
	public double getLength() {
		return super.getWidth();
	}
	
	@Override
	public String toString() {
		return getName()+" [length=" + getLength() + ", area()=" + area() + ", perimeter()="
				+ perimeter() + "]";
	}	
}
