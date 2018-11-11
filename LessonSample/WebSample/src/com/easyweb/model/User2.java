package com.easyweb.model;

import java.sql.Date;
import java.util.Arrays;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.easyweb.constraint.Hobbies;
import com.easyweb.constraint.Sex;
import com.easyweb.constraint.SqlDatePast;

public class User2 {
	private String id;

	@NotBlank(message = "账号为空")
	@Size(min = 2, max = 5, message = "账号长度必须在{min}和{max}之间")
	private String account;

	@NotBlank(message = "密码为空")
	@Size(min = 4, max = 5, message = "密码长度必须在{min}和{max}之间")
	private String passwd;

	@NotBlank(message = "确认密码为空")
	@Size(min = 4, max = 5, message = "确认密码长度必须在{min}和{max}之间")
	private String confirmPasswd;

	@Sex()
	private String sex;

	@Min(value = 1200, message = "工资必须大于等于1200")
	@Max(value = 50000, message = "工资必须小于等于50000")
	private Double salary;

	@SqlDatePast(message = "你只能输入2018-07-15及其之前的日期", value = "2018-07-15")
	private Date birthday;

	@NotNull(message = "爱好至少选择一项")
	@Hobbies()
	private String hobbies;

	public User2() {
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
		return "User2 [id=" + id + ",account=" + account + ", passwd=" + passwd + ", confirmPasswd=" + confirmPasswd + ", sex=" + sex + ", salary=" + salary + ", birthday=" + birthday + ", hobbies="
				+ (hobbies == null ? "null" : Arrays.asList(hobbies.split(",")).toString()) + "]";
	}

}
