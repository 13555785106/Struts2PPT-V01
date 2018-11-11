package org.sample;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;


public class Test02Action implements ServletRequestAware{
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		
		Integer counter = (Integer) request.getSession().getAttribute("counter");
		if (counter == null) {
			counter = 0;
		}
		counter++;
		request.getSession().setAttribute("counter", counter);
		return "success";
	}
}
