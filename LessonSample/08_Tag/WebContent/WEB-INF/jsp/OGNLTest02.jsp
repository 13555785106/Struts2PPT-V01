<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OGNL中的集合操作</title>
</head>
<body>
<h1>OGNL中的集合操作</h1>
	<s:set value="{'aa','bb','cc'}" var="list1" />
	<s:property value="#list1" />
	<br />
	<s:set
		value="{
	#{'name':'John','sex':'男','age':28},
	#{'name':'Tom','sex':'男','age':36},
	#{'name':'Jones','sex':'女','age':28},
	#{'name':'Gavin','sex':'男','age':28}}"
		var="students" />
	<s:property value="#students" />
	<s:property value="#students.{? #this.age==28}" />
	<br />
	<s:property value="#students.{^ #this.age==28}" />
	<br />
	<s:property value="#students.{$ #this.age==28}" />
	<br />
	<br />
	<s:if test="'aa' in #list1">
	'aa' 在 list1集合中<br/>
	</s:if>
	
	<s:if test="'dd' not in #list1">
	'dd' 不在 list1集合中<br/>
	</s:if>
</body>
</html>