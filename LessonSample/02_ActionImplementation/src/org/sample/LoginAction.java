package org.sample;

public class LoginAction {
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
	public String input() {
		return "input";
	}
	public String execute() {
		return "success";
	}
}
