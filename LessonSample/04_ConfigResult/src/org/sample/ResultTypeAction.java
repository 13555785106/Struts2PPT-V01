package org.sample;

import com.opensymphony.xwork2.ActionSupport;

public class ResultTypeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String getMessage() {
		return "Welcome to China!";
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String plainText() {
		return "plainText";
	}
	public String redirect() {
		return "redirect";
	}
	public String redirectAction() {
		return "redirectAction";
	}
}
