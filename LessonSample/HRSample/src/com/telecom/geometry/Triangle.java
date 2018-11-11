package com.telecom.geometry;

public class Triangle extends Shape {
	private double firstEdgeLength;
	private double secondEdgeLength;
	private double thirdEdgeLength;

	protected Triangle() {
		super("三角形");
	}

	public Triangle(double firstEdgeLength, double secondEdgeLength, double thirdEdgeLength) {
		this();
		setEdgeLength(firstEdgeLength, secondEdgeLength, thirdEdgeLength);
	}

	public void setEdgeLength(double firstEdgeLength, double secondEdgeLength, double thirdEdgeLength) {
		if (firstEdgeLength > 0 && secondEdgeLength > 0 && thirdEdgeLength > 0
				&& isValid(firstEdgeLength, secondEdgeLength, thirdEdgeLength)) {
			this.firstEdgeLength = firstEdgeLength;
			this.secondEdgeLength = secondEdgeLength;
			this.thirdEdgeLength = thirdEdgeLength;
		} else
			throw new IllegalArgumentException("长度小于等于0或者无法组成三角形");

	}

	public double getFirstEdgeLength() {
		return firstEdgeLength;
	}

	public void setFirstEdgeLength(double firstEdgeLength) {
		setEdgeLength(firstEdgeLength, secondEdgeLength, thirdEdgeLength);
	}

	public double getSecondEdgeLength() {
		return secondEdgeLength;
	}

	public void setSecondEdgeLength(double secondEdgeLength) {
		setEdgeLength(firstEdgeLength, secondEdgeLength, thirdEdgeLength);
	}

	public double getThirdEdgeLength() {
		return thirdEdgeLength;
	}

	public void setThirdEdgeLength(double thirdEdgeLength) {
		setEdgeLength(firstEdgeLength, secondEdgeLength, thirdEdgeLength);
	}

	@Override
	public double area() {
		double p = (firstEdgeLength + secondEdgeLength + thirdEdgeLength) / 2;
		return Math.sqrt(p * (p - firstEdgeLength) * (p - secondEdgeLength) * (p - thirdEdgeLength));
	}

	public static boolean isValid(double a, double b, double c) {
		if (a + b > c && b + c > a && a + c > b)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return getName() + " [firstEdgeLength=" + getFirstEdgeLength() + ",secondEdgeLength=" + getSecondEdgeLength()
				+ ",thirdEdgeLength=" + getThirdEdgeLength() + ",area()=" + area() + ",perimeter()=" + perimeter()
				+ "]";
	}

	@Override
	public double perimeter() {
		return firstEdgeLength + secondEdgeLength + thirdEdgeLength;
	}

	public static void main(String[] args) {
		Triangle tri = new Triangle(3, 4, 5);
		System.out.println(tri);
		tri.setEdgeLength(12, 16, 20);
		System.out.println(tri);
	}
}
