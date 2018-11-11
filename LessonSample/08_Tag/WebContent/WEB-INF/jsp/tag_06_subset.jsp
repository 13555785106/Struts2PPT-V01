<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>subset</title>
</head>
<body>
	<h1>subset</h1>
	<s:set var="list" value="{'0','1','2','3','4'}" />
	<s:subset source="#list" start="1" count="3" var="subList"/>
	<s:iterator value="#attr.subList">
		<li><s:property /></li>
	</s:iterator>
	<br/>
	<s:bean var="dotDecider" name="com.sample.DotDecider" />
	<s:subset source="{'0.1','1','2.1','3.2','4'}" decider="#dotDecider"
		var="newList" />
	<s:iterator value="#attr.newList">
		<li><s:property /></li>
	</s:iterator>
</body>
</html>