<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>push</title>
</head>
<body>
	<h1>push</h1>
	<s:bean name="com.sample.Student" var="s1">
		<s:param name="name" value="'John'" />
		<s:param name="sex" value="'male'" />
		<s:param name="age" value="32" />
	</s:bean>
	<s:push value="#s1">
		<s:property value="name" />
		<br />
		<s:property value="sex" />
		<br />
		<s:property value="age" />
		<br />
	</s:push>

</body>
</html>