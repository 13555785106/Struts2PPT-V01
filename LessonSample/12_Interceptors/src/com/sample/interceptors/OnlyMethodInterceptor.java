package com.sample.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class OnlyMethodInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	private String name;

	// 为该简单拦截器设置名字的setter方法
	public void setName(String name) {
		this.name = name;
	}
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		System.out.println(name+" 拦截的方法为："+invocation.getProxy().getMethod());
		return invocation.invoke();
	}

}
