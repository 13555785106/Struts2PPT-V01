package org.sample;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class Test03Action {

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer counter = (Integer) request.getSession().getAttribute("counter");
		if (counter == null) {
			counter = 0;
		}
		counter++;
		request.getSession().setAttribute("counter", counter);
		return "success";
	}
}
