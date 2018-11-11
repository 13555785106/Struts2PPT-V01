package com.sample;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.sample.constraints.Sex;

public class User {
	@NotNull(message = "账号不能为空!")
	@Size(min = 2, max = 3, message = "账号长度必须在{min}和{max}之间")
	private String account;
	@NotNull(message = "密码不能为空!")
	@Size(min = 4, max = 5, message = "密码长度必须在{min}和{max}之间")
	private String passwd;
	@Sex()
	private String sex;
	@Min(value = 0, message = "工资必须大于等于0")
	@Max(value = 50000, message = "工资必须小于等于50000")
	private Double salary;
	@Past(message = "你只能输入过去的日期")
	private Date birthday;

	private String[] hobbies;

	public User() {
	}

	public User(@NotNull String account, @NotNull String passwd) {
		setAccount(account);
		setPasswd(passwd);
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

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	public @NotNull @Range(min = 0, max = 5) Double getCommission(@NotNull @Range(min = 0, max = 1) Float pct) {
		return salary * pct;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", passwd=" + passwd + ", sex=" + sex + ", salary=" + salary + ", birthday="
				+ (birthday!=null?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(birthday):"null") + ", hobbies=" + (hobbies==null?"null":Arrays.asList(hobbies)) + "]";
	}

}
