package com.sample;

import java.sql.Date;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.DateRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.DoubleRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String account;
	private String passwd;
	private String retryPasswd;
	private String mobile;
	private String email;
	private Float salary;
	private Date birthday;
	private String hobbies;

	public String getAccount() {
		return account;
	}

	@RequiredStringValidator(key = "user.account.required", shortCircuit = true, message = "")
	@RegexFieldValidator(key = "user.account.regex", message = "", regex = "\\w{4,8}")
	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	@RequiredStringValidator(key = "user.passwd.required", shortCircuit = true, message = "")
	@RegexFieldValidator(key = "user.passwd.regex", message = "", regex = "\\w{4,8}")
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRetryPasswd() {
		return retryPasswd;
	}

	@RequiredStringValidator(key = "user.retryPasswd.required", message = "")
	@RegexFieldValidator(key = "user.retryPasswd.regex", message = "", regex = "\\w{4,8}")
	public void setRetryPasswd(String retryPasswd) {
		this.retryPasswd = retryPasswd;
	}

	public String getMobile() {
		return mobile;
	}

	@RegexFieldValidator(regex = "\\s*(\\d{11})?\\s*", message = "必须是有效手机号码格式")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	@EmailValidator(message = "必须是有效电子邮件格式")
	public void setEmail(String email) {
		this.email = email;
	}

	public Float getSalary() {
		return salary;
	}
	@ConversionErrorFieldValidator(message="工资只能填写数字！",shortCircuit=true)
	@DoubleRangeFieldValidator(minInclusive = "0", maxInclusive = "5000", message = "工资必须在 ${minInclusive} 至 ${maxInclusive}之间")
	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	@ConversionErrorFieldValidator(message="日期格式必须是 yyyy年MM月dd日 格式！",shortCircuit=true)
	@DateRangeFieldValidator(min = "2018年1月1日", max = "2018年1月31日", dateFormat = "yyyy年MM月dd日", message = "生日必须在 2018年1月1日 与 2018年1月31日 之间")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHobbies() {
		return hobbies;
	}
	@RequiredFieldValidator(shortCircuit = true, message = "爱好必须选择")
	//@RegexFieldValidator(regex = "([足篮排]球,)*([足篮排]球)", message = "爱好必须是足球、篮球、排球之一")
	@CustomValidator(type="hobbies",message="必须选择足球、篮球、排球！")
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String input() throws Exception {
		System.out.println(this);
		return super.input();
	}

	@Override
	public String execute() throws Exception {
		System.out.println(this);
		return super.execute();
	}

	@Override
	public String toString() {
		return "RegisterAction [account=" + account + ", passwd=" + passwd + ", mobile=" + mobile + ", email=" + email + ", salary=" + salary + ", birthday=" + birthday + ", hobbies=" + hobbies + "]";
	}

	@Override
	public void validate() {
		if (passwd != null && !passwd.equals(retryPasswd)) {
			addFieldError("retryPasswd", "重复密码必须与密码相同");
		}
	}

}
