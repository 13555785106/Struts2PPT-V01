package com.easyweb.model;

import java.sql.Date;
import java.util.Arrays;

public class User1 {
	private String id;
	private String account;
	private String passwd;
	private String confirmPasswd;
	private String sex;
	private Double salary;
	private Date birthday;
	private String hobbies;

	public User1() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "User1 [id=" + id + ",account=" + account + ", passwd=" + passwd + ", confirmPasswd=" + confirmPasswd + ", sex=" + sex + ", salary=" + salary + ", birthday=" + birthday + ", hobbies="
				+ (hobbies == null ? "null" : Arrays.asList(hobbies.split(",")).toString()) + "]";
	}

}
