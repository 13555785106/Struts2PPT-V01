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

public class User2Datas {
	private static User2Datas instance = null;
	Map<String, User2> datas = Collections.synchronizedMap(new HashMap<String, User2>());

	private User2Datas() {
		User2 user = new User2();
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
			user = new User2();
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

	public boolean chgUser(User2 user) {
		System.out.println(datas.keySet().contains(user.getId()));
		System.out.println(accountExist(user.getId(), user.getAccount()));
		if (datas.keySet().contains(user.getId()) && !accountExist(user.getId(), user.getAccount())) {
			return (datas.put(user.getId(), user) != null);
		}
		return false;
	}

	public User2 getUser(String id) {
		return datas.get(id);
	}

	public boolean addUser(User2 user) {
		if (!datas.keySet().contains(user.getId()) && !accountExist(user.getAccount())) {
			datas.put(user.getId(), user);
			return true;
		}
		return false;
	}

	public boolean accountExist(String account) {
		for (User2 user : datas.values()) {
			if (user.getAccount().equals(account))
				return true;
		}
		return false;
	}

	public boolean accountExist(String id, String account) {
		for (User2 user : datas.values()) {
			if (!user.getId().equals(id) && user.getAccount().equals(account))
				return true;
		}
		return false;
	}

	public List<User2> getDatas() {
		List<User2> list = new ArrayList<User2>(datas.values());
		Collections.sort(list, new Comparator<User2>() {
			@Override
			public int compare(User2 o1, User2 o2) {
				return o1.getAccount().compareTo(o2.getAccount());
			}

		});
		return list;
	}

	public User2 validateLogin(String account, String passwd) {
		for (User2 user : datas.values()) {
			if (user.getAccount().equals(account) && user.getPasswd().equals(passwd))
				return user;
		}

		return null;
	}

	public static User2Datas getInstance() {
		if (instance == null)
			instance = new User2Datas();
		return instance;
	}

}
