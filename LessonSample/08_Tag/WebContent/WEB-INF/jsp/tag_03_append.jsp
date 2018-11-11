<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>append</title>
</head>
<body>
	<h1>append</h1>
	<s:append var="newList">
		<s:param value="{'a1','a2','a3'}" />
		<s:param value="{'b1','b2','b3'}" />
	</s:append>
	<s:iterator value="#newList" var="e" status="st">
		<s:property value="#e" /><br/>
	</s:iterator>
</body>
</html>