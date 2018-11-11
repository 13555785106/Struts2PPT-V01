package org.sample;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.PreResultListener;

public class AttrAction extends ActionSupport{
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
		ActionContext.getContext().getActionInvocation().addPreResultListener(new PreResultListener() {

			@Override
			public void beforeResult(ActionInvocation invocation, String resultCode) {
				invocation.getInvocationContext().put("user", "tom");
				System.out.println(resultCode);
			}
		});
		return SUCCESS;
	}

}
