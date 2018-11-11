<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login-success</title>
</head>
<body>
	<s:actionmessage />
	<s:text name="account.label" />
	:
	<s:property value="account" />
	<br />
	<s:text name="passwd.label" />
	:
	<s:property value="passwd" />
	<br />
	<s:text name="login.welcome">
		<s:param>
			<s:property value="account" />
		</s:param>
		<s:param>
			<s:property value="passwd" />
		</s:param>
	</s:text>
	<br/>
	<s:debug/>
	<%
		Enumeration<String> attrNames = request.getAttributeNames();
		while(attrNames.hasMoreElements()){
			String attrName = attrNames.nextElement();
			Object attrValue = request.getAttribute(attrName);
			System.out.println(attrName + "=" + attrValue);
		}
	
	%>
</body>
</html>