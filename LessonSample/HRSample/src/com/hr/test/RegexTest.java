package com.hr.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		String str="hello world,hello java,hello java hello";
        Pattern p=Pattern.compile("hello$");
        Matcher m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }
	}
}
