<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.opensymphony.xwork2.ognl.*"%>
<%@page import="ognl.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>attr测试</title>
</head>
<body>

	<s:set var="city" value="'GuangZhou'" scope="application" />
	<s:set var="city" value="'ShangHai'" scope="session" />
	<s:set var="city" value="'BeiJing'" scope="request" />
	<s:set var="city" value="'HongKong'" scope="page" />
	<s:set var="city" value="'NewYork'" scope="action" />
	<%
	OgnlValueStack vs = (OgnlValueStack) request.getAttribute("struts.valueStack");
	Map<String, Object> map = vs.getContext();
	for(Map.Entry<String,Object> entry : map.entrySet()){
		System.out.println(entry.getKey()+"="+entry.getValue());
		System.out.println("-------------------------------");
	}
		/*Enumeration<String> names = request.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println("name=" + name);
			System.out.println("value=" + request.getAttribute(name));
		}*/
	%>
	city:<s:property value="city" />
	<br />
	#city:<s:property value="#city" />
	<br />action:
	<s:property value="#action.city" />
	<br />attr:
	<s:property value="#attr.city" />
	<br /> request:
	<s:property value="#request.city" />
	<br /> session:
	<s:property value="#session.city" />
	<br /> application:
	<s:property value="#application.city" />
	<br /> parameters:
	<s:property value="#parameters.city" />
	<br />
	<br />
	${pageScope.city}
	<s:debug />
</body>
</html>