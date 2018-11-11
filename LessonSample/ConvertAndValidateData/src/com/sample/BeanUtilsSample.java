package com.sample;

import java.util.Map;

public class BeanUtilsSample {

	public static void main(String[] args) {
		User user = new User();
		user.setAccount("sa");
		user.setPasswd("1234");
		user.setSex("男");
		user.setSalary(9000.0);
		user.setHobbies("足球,篮球,排球".split(","));
		user.setBirthday(new java.util.Date());
		System.out.println(user);
		//将bean转成Map<String,String>类型。
		//键为属性名，值都转成对应的字符串。
		Map<String, String> map = BeanUtils.bean2MapStr(user);
		System.out.println(map);
		//将Map<String,String>类型转成相应类型的bean。
		//键去掉前缀即为对应bean的属性名。
		user = BeanUtils.mapStr2Bean(map, User.class);
		System.out.println(user);

	}

}
