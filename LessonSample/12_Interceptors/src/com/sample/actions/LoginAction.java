package com.sample.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
		System.out.println("进入execute方法执行体..........");
		Thread.sleep(1500);
		if (account.equals("sa") && passwd.equals("123")) {
			//ServletActionContext.getRequest().getSession().setAttribute("account", "sa");
			ActionContext.getContext().getSession().put("account", "sa");
			return SUCCESS;
		}
		return ERROR;
	}
}
