package com.easyweb.model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User1Datas {
	private static User1Datas instance = null;
	Map<String, User1> datas = Collections.synchronizedMap(new HashMap<String, User1>());

	private User1Datas() {
		User1 user = new User1();
		user.setId("0");
		user.setAccount("sa");
		user.setPasswd("123");
		user.setConfirmPasswd("123");
		user.setSex("男");
		user.setBirthday(new Date(new java.util.Date().getTime()));
		user.setSalary(9000.0);
		user.setHobbies("足球,篮球");
		addUser(user);

		for (int i = 0; i < 115; i++) {
			user = new User1();
			user.setId(UUID.randomUUID().toString());
			user.setAccount("u" + new DecimalFormat("000").format(i));
			user.setPasswd("123");
			user.setConfirmPasswd("123");
			if (i % 2 == 1)
				user.setSex("男");
			else
				user.setSex("女");
			user.setBirthday(new Date(new java.util.Date().getTime()));
			user.setSalary(9000.0);
			user.setHobbies("足球,篮球,排球");
			addUser(user);
		}
	}

	public boolean delUser(String id) {
		return (datas.remove(id) != null);
	}

	public boolean chgUser(User1 user) {
		if (datas.keySet().contains(user.getId()))
			return (datas.put(user.getId(), user) != null);
		return false;
	}

	public User1 getUser(String id) {
		return datas.get(id);
	}

	public boolean addUser(User1 user) {
		if (!datas.keySet().contains(user.getId())) {
			datas.put(user.getId(), user);
			return true;
		}
		return false;
	}

	public List<User1> getDatas() {
		List<User1> list = new ArrayList<User1>(datas.values());
		Collections.sort(list, new Comparator<User1>() {
			@Override
			public int compare(User1 o1, User1 o2) {
				return o1.getAccount().compareTo(o2.getAccount());
			}

		});
		return list;
	}

	public User1 validateLogin(String account, String passwd) {
		for (User1 user : datas.values()) {
			if (user.getAccount().equals(account) && user.getPasswd().equals(passwd))
				return user;
		}

		return null;
	}

	public static User1Datas getInstance() {
		if (instance == null)
			instance = new User1Datas();
		return instance;
	}

}
