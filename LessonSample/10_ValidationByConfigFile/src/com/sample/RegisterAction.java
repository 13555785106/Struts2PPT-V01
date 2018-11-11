package com.sample;

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
	public String input() throws Exception {

		return super.input();
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user);
		return super.execute();
	}
	
}
