<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>set</title>
</head>
<body>
	<h1>set</h1>
	<s:set value="'John'" var="author"/>
	<s:set value="32" var="age"/>
	<s:set value="'Tom'" var="author" scope="request"/>
	<s:set value="28" var="age" scope="request"/>
	<s:property value="author"/><br/>
	<s:property value="age"/><br/>
	<s:property value="#author"/><br/>
	<s:property value="#age"/><br/>
	<s:property value="#request.author"/><br/>
	<s:property value="#request.age"/><br/>
	<s:debug/>
</body>
</html>