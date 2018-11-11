package com.telecom.geometry;

public abstract class Shape implements Comparable<Shape> {
	@Override
	public int compareTo(Shape other) {
		double ret = area() - other.area();
		if (ret > 0)
			return 1;
		else if (ret < 0)
			return -1;
		else
			return 0;
	}

	private String name = "未定义";

	public Shape(String name) {
		this.name = name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract double area();

	public abstract double perimeter();
}
