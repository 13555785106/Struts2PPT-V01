package com.sample.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map<String,Object> session = ctx.getSession();
		String user = (String)session.get("account");
		if (user != null)
		{
			return invocation.invoke();
		}
		ctx.put("tip" ,"您还没有登录，请以账号\"sa\"密码\"123\"登录系统");
		// 返回login的逻辑视图
		return Action.LOGIN;
	}

}
