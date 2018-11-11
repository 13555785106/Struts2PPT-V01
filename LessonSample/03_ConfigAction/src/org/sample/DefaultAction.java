package org.sample;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DefaultAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public String getNamespace() {
		return ServletActionContext.getActionMapping().getNamespace();
	}
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
}
