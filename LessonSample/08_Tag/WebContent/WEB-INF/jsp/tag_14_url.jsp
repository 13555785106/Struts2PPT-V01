<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>url</title>
</head>
<body>
	<h1>url</h1>
	<s:url action="test01" var="actionUrl">
		<s:param name="author" value="'John'" />
		<s:param name="age" value="#parameters.age" />
	</s:url>
	<s:property value="actionUrl" /><br/>
	<s:url value="/test.jsp" var="actionUrl">
		<s:param name="author" value="'John'" />
		<s:param name="age" value="#parameters.age" />
	</s:url>
	<s:property value="actionUrl" /><br/>
	<s:debug />
</body>
</html>