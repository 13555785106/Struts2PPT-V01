package com.telecom.geometry;

public class Ellipse extends Shape{
	public static final double PI = 3.1415926;
	
	private double majorRadius;
	private double minorRadius;

	protected Ellipse() {
		super("椭圆");
	}
	
	public Ellipse(double majorRadius, double minorRadius) {
		this();
		setMajorRadius(majorRadius);
		setMinorRadius(minorRadius);
	}

	public double getMajorRadius() {
		return majorRadius;
	}

	public void setMajorRadius(double majorRadius) {
		if (majorRadius > 0)
			this.majorRadius = majorRadius;
	}

	public double getMinorRadius() {
		return minorRadius;
	}

	public void setMinorRadius(double minorRadius) {
		if (minorRadius > 0)
			this.minorRadius = minorRadius;
	}

	@Override
	public double area() {
		return PI * majorRadius * minorRadius;
	}

	@Override
	public double perimeter() {
		return 2 * PI * (majorRadius > minorRadius ? minorRadius : majorRadius)
				+ 4 * (majorRadius > minorRadius ? majorRadius - minorRadius : -majorRadius + minorRadius);
	}

	@Override
	public String toString() {
		return getName()+" [majorRadius=" + majorRadius + ", minorRadius=" + minorRadius + ", area()=" + area()
				+ ", perimeter()=" + perimeter() + "]";
	}
}
