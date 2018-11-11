package org.sample;

import com.opensymphony.xwork2.ActionContext;

public class Test01Action {
	public String execute() {
		ActionContext ac = ActionContext.getContext();
		Integer counter = (Integer) ac.getSession().get("counter");
		if (counter == null) {
			counter = 0;
		}
		counter++;
		ac.getSession().put("counter", counter);
		return "success";
	}
}
