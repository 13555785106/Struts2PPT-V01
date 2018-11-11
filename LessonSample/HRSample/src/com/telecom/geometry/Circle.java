package com.telecom.geometry;

public class Circle extends Ellipse{
	public Circle(double radius) {
		setName("圆");
		setRadius(radius);
	}
	@Override
	public void setMajorRadius(double majorRadius) {
	}
	@Override
	public void setMinorRadius(double minorRadius) {
	}
	
	public void setRadius(double radius) {
		super.setMajorRadius(radius);
		super.setMinorRadius(radius);
	}
	public double getRadius() {
		return super.getMajorRadius();
	}
	
	@Override
	public String toString() {
		return getName()+" [radius=" + getRadius() + ", area()=" + area()
				+ ", perimeter()=" + perimeter() + "]";
	}
}
