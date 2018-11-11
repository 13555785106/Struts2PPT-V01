package com.sample;

import com.opensymphony.xwork2.ActionSupport;

public class Test01Action extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String author;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(getAuthor());
		return SUCCESS;
	}

}
