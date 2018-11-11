package com.sample;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		clearFieldErrors();
		if (user == null)
			return INPUT;
		boolean isValid = true;
		if (StringUtils.isEmpty(user.getAccount())) {
			addFieldError("user.account", "账号为空");
			isValid = false;
		}
		if (StringUtils.isEmpty(user.getPasswd())) {
			addFieldError("user.passwd", "密码为空");
			isValid = false;
		} 
		if (user.getBirthday() == null) {
			addFieldError("user.birthday", "生日为空");
			isValid = false;
		}
		if (isValid) {
			addActionMessage("注册成功");
			return SUCCESS;
		} else
			return ERROR;
	}

}
