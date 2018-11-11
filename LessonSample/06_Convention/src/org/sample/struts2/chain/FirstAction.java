package org.sample.struts2.chain;

import com.opensymphony.xwork2.ActionSupport;

public class FirstAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(this.getClass().getName());
		addActionMessage("第一个Action添加的信息");
		return "second";
	}
}
