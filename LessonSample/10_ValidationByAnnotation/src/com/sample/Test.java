package com.sample;

public class Test {

	public static void main(String[] args) {
		String str = "篮球,排d球,篮球,足球";
		System.out.println(str.matches("([足篮排]球,)*([足篮排]球)"));

	}

}
