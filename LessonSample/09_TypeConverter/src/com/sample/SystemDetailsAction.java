package com.sample;

import com.opensymphony.xwork2.ActionSupport;

public class SystemDetailsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Environment environment = new Environment("Development");
	private String operatingSystem = "Windows XP SP3";

	public String execute() {
		return SUCCESS;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
