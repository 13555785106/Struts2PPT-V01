<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lambda表达式</title>
</head>
<body>
	<h1>Lambda表达式</h1>
	<s:property
		value="#fib =:[#this==0 ? 0 : #this==1 ? 1 : #fib(#this-2)+#fib(#this-1)],#fib(11)" />
	<br />
	<s:property value="#f = :[#this==1?1:#this*#f(#this-1)] , #f(4)" />
	<br>
	<s:property value="#conv =:[#this==1?'男':#this==2?'女':''], #conv(1)" />
	<br/>
</body>
</html>