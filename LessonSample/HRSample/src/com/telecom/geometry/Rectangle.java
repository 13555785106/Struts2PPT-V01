package com.telecom.geometry;

public class Rectangle extends Shape{
	private double width;
	private double height;
	
	protected Rectangle() {
		super("矩形");
	}

	public Rectangle(double width, double height) {
		this();
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(double width) {
		if (width > 0)
			this.width = width;
		else
			throw new IllegalArgumentException("width必须大于0");
	}

	public double getWidth() {
		return width;
	}

	public void setHeight(double height) {
		if (height > 0)
			this.height = height;
		else
			throw new IllegalArgumentException("height必须大于0");
	}

	public double getHeight() {
		return height;
	}

	@Override
	public double area() {
		return width * height;
	}

	@Override
	public double perimeter() {
		return (width + height) * 2;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle rect = (Rectangle)obj;
			return this.width == rect.getWidth()
					&& this.height == rect.getHeight();
		}
		return false;
	}

	@Override
	public String toString() {
		return getName()+" [width=" + width + ", height=" + height + ", area()=" + area() + ", perimeter()="
				+ perimeter() + "]";
	}


}
