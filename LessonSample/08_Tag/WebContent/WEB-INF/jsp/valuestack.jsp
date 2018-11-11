<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@page import="com.opensymphony.xwork2.ognl.OgnlValueStack"%>
<%@page import="org.apache.struts2.util.AttributeMap"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ValueStack测试</title>
</head>
<body>
	<%
		/*Enumeration<String> names = request.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println("name=" + name);
			//System.out.println("value=" + request.getAttribute(name));
		}*/
		
		ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
		System.out.println(vs);
		vs.push("Hello World! 001");
		vs.set("banana", "香蕉");
		vs.push("Hello World! 002");
		vs.set("apple", "苹果");
		vs.push("Hello World! 003");
		vs.set("peach", "桃子");
		CompoundRoot compoundRoot = vs.getRoot();
		System.out.println(compoundRoot);
		for(int i=0;i<compoundRoot.size();i++){
			System.out.print(i+" = ");
			System.out.println(compoundRoot.get(i));
		}
		vs.getContext().put("apple", "果果");
		for(Map.Entry<String,Object> entry : vs.getContext().entrySet()){
			String key = entry.getKey();
			System.out.println(entry.getKey());
			
			if(key.equals("attr")){
				System.out.println("\t"+vs.getContext().get(key).getClass().getName());
				AttributeMap am = (AttributeMap)vs.getContext().get(key);
				System.out.println(am.values());
				/*for(Object k1 : am.keySet()){
					System.out.println("\t"+k1);
					System.out.println("\t\t"+am.get(k1));
				}*/
				
			}
		}
		/*while (vs.size() > 0) {
			Object obj = vs.pop();
			System.out.println(obj.getClass().getSimpleName());
			System.out.println(obj);
		}*/
	%>


	<s:property value="top" />
	<br />
	<br />
	<s:property value="[0]" />
	<br />
	<br />
	<s:property value="apple" />
	<br />
	<s:property value="banana" />
	<br />
	<s:property value="peach" />
	<br />
	<br />
	<s:debug />
</body>
</html>