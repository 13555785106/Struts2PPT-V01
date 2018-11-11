package com.sample.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SimpleInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// 简单拦截器的名字
	private String name;

	// 为该简单拦截器设置名字的setter方法
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println(name + " 拦截器的动作---------" + "开始执行Action的时间为：" + sdf.format(new Date()));
		long start = System.currentTimeMillis();
		
		invocation.getInvocationContext().getValueStack().set("helloWorld", "你好世界");
		
		String result = invocation.invoke();
		
		System.out.println(name + " 拦截器的动作---------" + "执行完Action的时间为：" + sdf.format(new Date()));
		System.out.println(name + " 拦截器的返回结果---------"+result);
		long end = System.currentTimeMillis();
		System.out.println(name + " 拦截器的动作---------" + "执行完该Action耗时:" + (end - start) + "毫秒");
		return result;
	}
}