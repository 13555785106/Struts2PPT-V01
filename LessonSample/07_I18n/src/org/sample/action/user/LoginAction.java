package org.sample.action.user;


import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@AllowedMethods({ "input", "execute" })
@Results({@Result(name="success",location="login-success.jsp"),
    @Result(name="input",location="login-input.jsp"),
    @Result(name="error",location="login-input.jsp")})
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String account;
	private String passwd;

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

	@Override
	public String execute() throws Exception {
		if (account.equals("sa") && passwd.equals("123")) {
			//clearMessages();
			addActionMessage(getText("login.success"));
			return SUCCESS;
		}else {
			addActionError(getText("Login failed!"));
			return ERROR;
		}
	}

	@Override
	public String input() throws Exception {
		return super.input();
	}

}
