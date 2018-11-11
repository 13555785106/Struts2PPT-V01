package org.sample;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class MethodAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String methodName;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getNamespace() {
		return ServletActionContext.getActionMapping().getNamespace();
	}

	@Override
	public String input() throws Exception {
		setMethodName("input");
		return super.input();
	}

	@Override
	public String execute() throws Exception {
		setMethodName("execute");
		return super.execute();
	}

	public String firstMethod() {
		setMethodName("firstMethod");
		return "firstMethod";
	}

	public String secondMethod() {
		setMethodName("secondMethod");
		return "secondMethod";
	}

	public String thirdMethod() {
		setMethodName("thirdMethod");
		return "thirdMethod";
	}
}
